import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Created by zqian on 12/10/2016.
 */
public class ContainerNode extends Node{


    @Override
    protected void paint(Graphics2D g){
        super.paint(g);
        for(Node nn: children){
            AffineTransform trns = g.getTransform();
            trns.translate(nn.getPos().getX(), nn.getPos().getY());
            g.setTransform(trns);
            nn.paint(g);
        }
    }

    @Override
    public void addChild(Node n){
        children.add(n);
        boundUpdate(n,true);
    }

    public void removeChild(Node n){
        children.remove(n);
        boundUpdate(n,false);
    }

    private void boundUpdate(Node n, boolean add){
        if(add){
            // compare the new added node with the existing boundary
            bound = bound.createUnion(n.bound).getBounds();
        }
        else{
            // when deleting a node, recalculate the boundary
            if(!children.isEmpty()) {
                bound = children.get(0).bound;
                for (Node nn : children){
                    bound = bound.createUnion(nn.bound).getBounds();
                }
            }
        }

    }
}
