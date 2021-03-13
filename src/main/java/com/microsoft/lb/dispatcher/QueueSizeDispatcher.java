package com.microsoft.lb.dispatcher;

import com.microsoft.lb.dispatcher.api.TaskDispatcher;
import com.microsoft.lb.node.api.ExecutorNode;
import com.microsoft.lb.task.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueueSizeDispatcher implements TaskDispatcher {
    private List<QSizeComparableNode> list = new ArrayList<>();
    @Override
    public void dispatch(Task task) {

        if(!task.getType().equals(Task.EOF)) {
            if(list.size() > 1){
                Collections.sort(list);
            }
            list.get(0).getNode().execute(task);
        }else{
            list.stream().forEach(n -> n.getNode().execute(task));
        }
    }

    @Override
    public void addNode(ExecutorNode node) {
        list.add(new QSizeComparableNode(node));
    }

    private class QSizeComparableNode implements Comparable<QSizeComparableNode>{
        private ExecutorNode node;

        public QSizeComparableNode(ExecutorNode node) {
            this.node = node;
        }

        public ExecutorNode getNode() {
            return node;
        }

        @Override
        public int compareTo(QSizeComparableNode other) {
            int q1 = this.node.getQueueSize();
            int q2 = other.node.getQueueSize();
            if (q1 < q2) {
                return -1;
            } else if (q1 > q2) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
