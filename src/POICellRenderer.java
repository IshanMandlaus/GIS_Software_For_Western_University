import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;
import java.awt.*;
import java.util.ArrayList;
import java.util.Locale;

public class POICellRenderer extends DefaultListCellRenderer{
    public POICellRenderer(){

    }
    public Component getListCellRendererComponent(JList list,
                                                  Object value, int index, boolean isSelected,
                                                  boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value,index,isSelected,cellHasFocus);
        POI p = (POI)value;
        setText(p.getName());

        for (String user : p.getFavUsers()){
            if (user.equals(p.getContainingLayer().getCurrUser().getUsername())){
                setBackground(new Color(255,215,0));
            }
        }
        return this;
    }
}

