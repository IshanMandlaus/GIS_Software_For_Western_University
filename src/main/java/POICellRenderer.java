/**
 A custom cell renderer for a JList that displays Points of Interest (POIs).
 POICellRenderer extends DefaultListCellRenderer and overrides its getListCellRendererComponent method.
 */
 import javax.swing.DefaultListCellRenderer;
 import javax.swing.JList;
 import java.awt.*;
 public class POICellRenderer extends DefaultListCellRenderer {

     /**
     * Constructs a new POICellRenderer object.
     */
    public POICellRenderer() {}

    /**
     * Returns a component that has been configured to display the specified value.
     *
     * @param list          the JList we're painting
     * @param value         the value returned by list.getModel().getElementAt(index)
     * @param index         the cell index
     * @param isSelected   true if the specified cell was selected
     * @param cellHasFocus  true if the specified cell has the focus
     * @return              a component whose display properties have been configured according to the specified parameters
     */
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        super.getListCellRendererComponent(list, value,index,isSelected,cellHasFocus);
        POI p = (POI)value;
        // Set the text to be displayed in the list to be the POI's name
        setText(p.getName());

        // Set the background color of the cell to yellow if the current user has favorited this POI
        for (String user : p.getFavUsers()){
            if (user.equals(p.getContainingLayer().getCurrUser().getUsername())){
                setBackground(new Color(255,215,0));
            }
        }

        return this;
    }

    public void searchFunction() {
        setBackground(new Color(255,215,0));
    }
}

