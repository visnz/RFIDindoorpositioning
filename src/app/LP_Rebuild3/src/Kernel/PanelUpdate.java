package Kernel;

import java.awt.*;
import java.util.LinkedList;

/**
 * 此类包含一个链表对象保存一系列的Component
 * 可用于遍历刷新
 *
 * 不过好像也没用上
 * Created by zyvis on 2017/4/13.
 */
public class PanelUpdate implements Runnable {
    protected LinkedList<Component> list=new LinkedList<Component>();
    @Override
    public void run() {
        while(true) {
                allUpdate();
                //defaultLog.report("PanelUpdate thread update successfully");
        }
    }
    public void addComp(Component c){
        list.add(c);
    }
    public void allUpdate(){
        for (Component e:
             list) {
            e.repaint();
        }
    }

}
