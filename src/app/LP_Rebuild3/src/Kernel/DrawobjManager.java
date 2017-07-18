package Kernel;

import Objects.Drawobj;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zyvis on 2017/3/5.
 * 可画图像（Actor、Tag等）的管理器，未用上
 */
public class DrawobjManager {
    protected List<Drawobj> Taglist =new ArrayList<>();
    public void addTag(Drawobj drawobj){
        Taglist.add(drawobj);
    }
}
