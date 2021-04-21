/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cibps;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mr Zorro
 */
public class myTableModel extends AbstractTableModel {

    Vector datas = new Vector();

    boolean[] columnsVisible = new boolean[10];

    public myTableModel() {
        setAllVisible();
    }

    public void setAllVisible() {
        for (int i = 0; i < columnsVisible.length; i++) {
            columnsVisible[i] = true;
        }
    }

    public void setVisible(int index, boolean value) {
        if (index >=0 && index < columnsVisible.length) {
            columnsVisible[index] = false;
        }
    }

    public boolean isVisible(int index) {
        if (index >=0 && index < columnsVisible.length) {
            return columnsVisible[index];
        }
        else
            return true;
    }
    
    public int getRowCount() {
        return datas.size();
    }

    public int getColumnCount() {
        int n = 0;
        for (int i = 0; i < 10; i++)
            if (columnsVisible[i]) n++;
        return n;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        Object[] array = (Object[])(datas.elementAt(rowIndex));
        return array[getNumber(columnIndex)];
    }

    protected int getNumber(int col) {
        int n = col;    // right number to return
        int i = 0;
        do {
            if (!(columnsVisible[i]))
                n++;
            i++;
        } while (i < n && i < columnsVisible.length);
        // If we are on an invisible column,
        // we have to go one step further
        while (n < columnsVisible.length && !(columnsVisible[n]))
            n++;
        return n;
    }
}
