//package org.activiti.explorer.util;
//
//import java.util.Iterator;
//import javax.annotation.Resource;
//import org.activiti.engine.impl.bpmn.behavior.ExclusiveGatewayActivityBehavior;
//import org.activiti.engine.impl.pvm.PvmTransition;
//import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
////import org.apache.commons.lang.StringUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.codehaus.groovy.bsf.GroovyEngine;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
////import com.redxun.bpm.core.entity.config.ExclusiveGatewayConfig;
////import com.redxun.bpm.core.entity.config.NodeExecuteScript;
////import com.redxun.bpm.core.manager.BpmNodeSetManager;
////import com.redxun.core.script.GroovyEngine;
////import com.sun.star.uno.RuntimeException;
///**
// * 对网关的条件判断，优先使用扩展的配置
// * @author keitch
// *
// */
//@SuppressWarnings("serial")
//public class ExclusiveGatewayActivityBehaviorExt extends ExclusiveGatewayActivityBehavior{
//
//    protected static Logger log = LoggerFactory.getLogger(ExclusiveGatewayActivityBehaviorExt.class);
//
//    //节点的设置管理器
//    @Resource
//    private BpmNodeSetManager bpmNodeSetManager;
//    //脚本引擎
//    @Resource
//    private GroovyEngine groovyEngine;
//
//    @Override
//    protected void leave(ActivityExecution execution) {
//        log.debug("enter ExclusiveGatewayActivityBehaviorExt=======================");
//        if (log.isDebugEnabled()) {
//            log.debug("Leaving activity '{}'", execution.getActivity().getId());
//         }
//        String solId=(String)execution.getVariable("solId");
//        String nodeId=execution.getActivity().getId();
//        log.debug("solid is {} and nodeId is {}",solId,nodeId);
//
//        if(StringUtils.isNotEmpty(solId)&& StringUtils.isNotBlank(nodeId)){
//            ExclusiveGatewayConfig configs=bpmNodeSetManager.getExclusiveGatewayConfig(solId, nodeId);
//            for(NodeExecuteScript script:configs.getConditions()){
//                String destNodeId=script.getNodeId();
//                String condition=script.getCondition();
//                log.debug("dest node:{}, condition is {}",destNodeId,condition);
//                //执行脚本引擎
//                Object boolVal=groovyEngine.executeScripts(condition, execution.getVariables());
//                if(boolVal instanceof Boolean){
//                    Boolean returnVal=(Boolean)boolVal;//符合条件
//                    if(returnVal==true){
//                        //找到符合条件的目标节点并且进行跳转
//                        Iterator<PvmTransition> transitionIterator = execution.getActivity().getOutgoingTransitions().iterator();
//                        while (transitionIterator.hasNext()) {
//                              PvmTransition seqFlow = transitionIterator.next();
//                              if(destNodeId.equals(seqFlow.getDestination().getId())){
//                                  execution.take(seqFlow);
//                                  return;
//                              }
//                        }
//                    }
//                }else{
//                    throw new RuntimeException("表达式:\n "+condition+"\n返回值不为布尔值(true or false)");
//                }
//            }
//        }
//        //执行父类的写法，以使其还是可以支持旧式的在跳出线上写条件的做法
//        super.leave(execution);
//
//    }
//}