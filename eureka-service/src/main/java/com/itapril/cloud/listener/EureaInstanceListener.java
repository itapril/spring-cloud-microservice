package com.itapril.cloud.listener;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import org.joda.time.DateTime;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by itapril on 2018/5/28 9:29.
 */
@Configuration
@EnableScheduling
public class EureaInstanceListener implements ApplicationListener{

    private ConcurrentHashMap<String,LostInstance> lostInstanceMap = new ConcurrentHashMap<>();

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if(applicationEvent instanceof EurekaInstanceCanceledEvent){
            EurekaInstanceCanceledEvent event = (EurekaInstanceCanceledEvent) applicationEvent;
            PeerAwareInstanceRegistry register = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
            Applications applications = register.getApplications();
            List<Application> applicationList = applications.getRegisteredApplications();
            for (Application application : applicationList) {
                List<InstanceInfo> instanceInfos = application.getInstances();
                for (InstanceInfo instance : instanceInfos) {
                    String instanceId = instance.getInstanceId();
                    if(instanceId.equals(event.getServerId())){
                        lostInstanceMap.remove(instanceId);
                        lostInstanceMap.put(instanceId,new LostInstance(instance));
                    }
                }
            }
            System.out.println(event.getServerId() + " 服务停机。。。。");
        }

        if(applicationEvent instanceof EurekaInstanceRegisteredEvent){
            EurekaInstanceRegisteredEvent event = (EurekaInstanceRegisteredEvent) applicationEvent;
            System.out.println(event.getInstanceInfo().getInstanceId() + " 服务注册成功....");
            lostInstanceMap.remove(event.getInstanceInfo().getInstanceId());
        }
    }
    private int[] defaultNotifyInterval = {0,30,60,90,120,150};
    @Scheduled(cron = "0/30 * * * * ?")
    private void nodifyLostInstance(){
        System.out.println("....scheduled is working....");
        Iterator<Map.Entry<String,LostInstance>> iterator = lostInstanceMap.entrySet().iterator();
        while (iterator.hasNext()){
            Map.Entry<String,LostInstance> entry = iterator.next();
            String key = entry.getKey();
            LostInstance value = entry.getValue();
            DateTime dt = new DateTime(value.getLostTime());
            if(dt.plusSeconds(defaultNotifyInterval[value.getCurrentInterval()]).isBeforeNow()){
                System.out.println("服务："+value.getInstanceId()+"已失效,IP为："+value.getIPAddr()+",失效时间为：" +dt.toString());
            }
        }
    }


    class LostInstance extends InstanceInfo{
        protected int currentInterval = 0;
        protected Date lostTime;
        public LostInstance(InstanceInfo ii) {
            super(ii);
            this.lostTime = new Date();
        }

        public Date getLostTime() {
            return lostTime;
        }

        public void setLostTime(Date lostTime) {
            this.lostTime = lostTime;
        }
        public int getCurrentInterval(){
            return currentInterval++%4;
        }
    }

}
