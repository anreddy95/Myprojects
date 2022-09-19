    import javax.swing.*;  
    import javax.swing.event.*;  
    import java.awt.*;  
    import java.util.*;  
    class AcmeUsedCars implements ListSelectionListener  
    {  
      private ArrayList carsForSale = new ArrayList();  
      private ArrayList carsSold = new ArrayList();  
      private String[][] options = {{"Sell Car","Buy Car","Sales Figures","Quit"},  
                                    {"Buy","Cancel"},{"Sell","Cancel"}};  
      private JPanel[] panel = new JPanel[2];  
      private DefaultListModel listModel = new DefaultListModel();  
      private JList list= new JList(listModel);  
      private JScrollPane sp = new JScrollPane(list);  
      private JTextField[] tf = new JTextField[7];  
      private JLabel[] lbl = new JLabel[7];  
      private int carIndex = 0;  
      public AcmeUsedCars()  
      {  
        buildPanels();  
        int selection = 0;  
        while(selection != options[0].length - 1)  
        {  
          selection = JOptionPane.showOptionDialog(null,"Welcome to the Acme Used Cars Program. "+  
          "Please select an option:","Acme Used Cars",-1,-1,null,options[0],"");  
          if(selection == 0) sellCar();  
          else if(selection == 1) buyCar();  
          else if(selection == 2) salesFigures();  
        }  
        JOptionPane.showMessageDialog(null,"Thank you for using the Acme Used Car Program\n\nGoodbye.");  
        System.exit(0);  
      }  
      private void sellCar()  
      {  
        listModel.clear();  
        Car temp;  
        for(int x = 0; x < carsForSale.size(); x++)  
        {  
          temp = (Car)carsForSale.get(x);  
          listModel.addElement(""+temp.year+" "+temp.make+" $"+temp.sellingPrice);  
        }  
        int selectionB = JOptionPane.showOptionDialog(null,panel[0],"Acme Used Cars",-1,-1,null,options[2],"");  
        while(carIndex < 0)  
        {  
          JOptionPane.showMessageDialog(null,"No car selected, try again");  
          selectionB = JOptionPane.showOptionDialog(null,panel[0],"Acme Used Cars",-1,-1,null,options[2],"");  
          if(selectionB == 1) break;  
        }  
        if(selectionB == 0)  
        {  
          ((Car)carsForSale.get(carIndex)).soldFor(Double.parseDouble(  
                JOptionPane.showInputDialog("Enter Sale Price eg 1500.00")));  
          carsSold.add(carsForSale.get(carIndex));  
          carsForSale.remove(carIndex);  
        }  
      }  
      private void buyCar()  
      {  
        int selectionD = 0;  
        boolean validData = true;  
        while(selectionD != 1 || !validData)  
        {  
          for(int x = 0; x < tf.length; x++) tf[x].setText("");  
          selectionD = JOptionPane.showOptionDialog(null,panel[1],"Acme Used Cars",-1,-1,null,options[1],"");  
          if(selectionD != options[1].length - 1)  
          {  
            for(int x = 0; x < tf.length-2; x++)  
            {  
              if(tf[x].getText().equals(""))  
              {  
                validData = false;  
                break;  
              }  
            }  
            try  
            {  
              double temp = Double.parseDouble(tf[5].getText());  
              temp = Double.parseDouble(tf[6].getText());  
            }  
            catch(Exception e){validData = false;}  
            if(validData)  
            {  
              carsForSale.add(new Car(tf[0].getText(),tf[1].getText(),tf[2].getText(),tf[3].getText(),  
               tf[4].getText(),Double.parseDouble(tf[5].getText()),Double.parseDouble(tf[6].getText())));  
            }  
          }  
        }  
      }  
      private void salesFigures()  
      {  
        listModel.clear();  
        Car temp;  
        for(int x = 0; x < carsSold.size(); x++)  
        {  
          temp = (Car)carsSold.get(x);  
          listModel.addElement(""+temp.year+" "+temp.make+" $"+temp.sellingPrice+" Profit = $"+temp.profitMade);  
        }  
        JOptionPane.showMessageDialog(null,panel[0],"Sales Figures",-1);  
      }  
      private void buildPanels()  
      {  
        panel[0] = new JPanel();  
        list.addListSelectionListener(this);  
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);  
        sp.setPreferredSize(new Dimension(250,400));  
        panel[0].add(sp);  
        panel[1] = new JPanel(new GridBagLayout());  
        GridBagConstraints gbc = new GridBagConstraints();  
        gbc.ipady = 10;  
        String[] labelText = {"Make :","Model :","Year :","Miles :","Color :","Purchase Price :","Selling Price :"};  
        for(int x = 0; x < tf.length; x++)  
        {  
          gbc.gridy = x;  
          gbc.gridx = 0;  
          lbl[x] = new JLabel(labelText[x]);  
          gbc.anchor = gbc.EAST;  
          panel[1].add(lbl[x],gbc);  
          gbc.gridx = 1;  
          tf[x] = new JTextField(15);  
          gbc.anchor = gbc.WEST;  
          panel[1].add(tf[x],gbc);  
        }  
      }  
      public void valueChanged(ListSelectionEvent lse){carIndex = list.getSelectedIndex();}  
      public static void main(String[] args){new AcmeUsedCars();}  
    }  
    class Car  
    {  
      String make;  
      String model;  
      String year;  
      String miles;  
      String color;  
      double purchasePrice;  
      double sellingPrice;  
      double profitMade;  
      boolean sold = false;  
      public Car(String ma,String mo,String yr,String mi,String co,double pp,double sp)  
      {  
        make = ma; model = mo; year = yr; miles = mi; color = co;  
        purchasePrice = pp; sellingPrice = sp;  
      }  
      public void soldFor(double salePrice)  
      {  
        sellingPrice = salePrice;  
        profitMade = sellingPrice - purchasePrice;  
        sold = true;  
      }  
    }  