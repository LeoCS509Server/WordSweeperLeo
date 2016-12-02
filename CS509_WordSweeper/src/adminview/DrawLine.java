package adminview;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


import adminmodel.*;


public class DrawLine {  
	
    public static void main(String[] args) {   
        new DrawSee("Admin");
    }   
}   

class DrawSee<globalboard> extends JFrame {
	
	
    private static final int sx = 300;//x coordinate of square
    private static final int sy = 150;//y coordinate of square
    private static final int w = 40;
    int rw;
    
    private Graphics jg;
    
    
    
    private Color rectColor = new Color(0xf5f5f5);
    
    public DrawSee(String name) {
    	super(name);
        Container p = getContentPane();
        setBounds(400, 100, 700, 500);
        setVisible(true);
        p.setBackground(rectColor);
        setLayout(null);   
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(275, 100, 350, 350);
		getContentPane().add(scrollPane);
		
		JPanel panel = new JPanel();
		scrollPane.add(panel);
		setVisible(true);
		
		JButton button = new JButton("Refresh");
		button.setBounds(270, 56, 80, 25);
		getContentPane().add(button);
		button.addActionListener(new refreshAction());
		
	    Choice gameChoice = new Choice();
		gameChoice.setBounds(45,55, 100, 30);
		getContentPane().add(gameChoice);
		
		gameChoice.add("Apple");
		gameChoice.add("Grapes");
		gameChoice.add("Mango");
		gameChoice.add("Peer");
		
		
		JButton button2 = new JButton("Select");
		button2.setBounds(175, 56, 80, 25);
		getContentPane().add(button2);
		button2.addActionListener(new selectAction());

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(50, 100, 200, 350);
		getContentPane().add(scrollPane2);
		
		JList<String> playerlist = new JList<>(new MyListModel());
		scrollPane2.setViewportView(playerlist);

        try {           
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }        

      
        jg =  this.getGraphics();
        
        // draw
        paintComponents(jg);
      
        
    }
    
    class selectAction implements ActionListener{
    	@Override
   	 public void actionPerformed(ActionEvent e) {
   	      paintComponents(jg);     ///
   	    }
    }
    
    
	class refreshAction implements ActionListener{
    	@Override
    	 public void actionPerformed(ActionEvent e) {
    	           ///
    	    }
    }
    
    class MyListModel extends AbstractListModel<String>{
    	private String[] contents={"1","2"};
    	@Override
    	public String getElementAt(int x){
    		if (x<contents.length)
    			return contents[x++];
    		else
    			return null;
    	}
    	@Override
    	public int getSize(){
    		return contents.length;
    	}
    }
    
    public void paintComponents(Graphics g) {
     
            rw=Board.size*40;
           
           
            // set line color
            g.setColor(Color.BLACK);
            
            // draw outer square
            g.drawRect(sx, sy, rw, rw);
            
            
            for(int i = 0; i < Board.size; i ++) {
                // draw inner square
                for(int j=0;j<Board.size;j++){
                g.drawRect(sx + (i * w), sy + (j * w), w, w);}
            }   
                // fill the square
            for(int i=0;i< Player.playerlocation.length;i++){
            	Color Color = new Color(220+(int)(Math.random()*35),220+(int)(Math.random()*35),220+(int)(Math.random()*35));
    			g.setColor(Color);
    			g.fillRect((sx+(Player.playerlocation[i]/10)*40),(sy+(Player.playerlocation[i]%10)*40), 160, 160);
            }
          
    
            for(int i = 0; i <=Board.size; i ++){
                for(int j = 0; j < Board.size; j ++) {
                    drawString(g, j, i);                    
                }
            }
      
    }
    
    
    private void drawString(Graphics g, int x, int y) {
    	             g.setColor(Color.BLACK);
    	             g.setFont(new Font("Arial", 0, 25)); 
    	             g.drawString(Board.globalboard[x][y] + "", sx + (y  * w) + 5, sy + ((x + 1) * w) - 5);
    	      
    	     }
    	 
    
}