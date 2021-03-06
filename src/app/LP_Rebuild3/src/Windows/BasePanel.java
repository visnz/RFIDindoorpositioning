package Windows;

import Loger.defaultLog;
import MiniProperties.Framesize;
import Objects.Actor;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Created by zyvis on 2016/11/28.
 */
public class BasePanel extends JPanel {
    //private Waypoint mouse,
    protected BufferedImage baseImage=null;
    protected LinkedList<Actor> objlist =new LinkedList<>();
    protected Iterator<Actor> actorIterator;
    public  BufferedImage getBaseImage() {
        return baseImage;
    }
    public void setBaseImage(BufferedImage baseImage) {
        this.baseImage = baseImage;
        defaultLog.report("base image installed");
    }
    protected Framesize framesize;
    public BasePanel() {
        if(baseImage!=null)framesize=new Framesize(baseImage.getWidth(),baseImage.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(framesize==null)framesize=new Framesize(baseImage.getWidth(),baseImage.getHeight());
        g.drawImage(baseImage,0,0,null);
        Actor tmp;
        defaultLog.report("draw obj");
        actorIterator=objlist.iterator();
        while(actorIterator.hasNext()) {
            tmp = actorIterator.next();
            g.drawImage(tmp.getDrawobjImage().getImage(),
                    tmp.getWaypoint().getMidX()-tmp.getFramesize().getWidth()/2,
                    tmp.getWaypoint().getMidY()-tmp.getFramesize().getHeight()/2,
                    null);
        }

    }
    public void add(Actor obj){
        objlist.add(obj);
        defaultLog.report("actor obj added successfully");
        this.repaint();
    }
    public void clear(){
        objlist.clear();
        paint(baseImage.getGraphics());
        defaultLog.report("objlist.isEmpty()"+objlist.isEmpty());
    }

}

