package admin.adminview;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import admin.admincontroller.ListGamesController;
import admin.admincontroller.ShowGameStateController;
import admin.adminmodel1.AdminModel;
import admin.adminmodel1.Game;
import client.ServerAccess;



 
	  
  

public class DrawSee extends JFrame {
	
	private static class DrawseeHolder{
		private static final DrawSee gui=new DrawSee(model);
	}
	
	public static DrawSee getGUI(){
		return DrawseeHolder.gui;
	}
	
	
    private static final int sx = 300;//x coordinate of square
    private static final int sy = 150;//y coordinate of square
    private static final int w = 40;
    int rw;
    
    JScrollPane scrollPane;
	JPanel panel;
	JButton button;
	JButton button2;
	Choice gameChoice;
	JScrollPane scrollPane2;
	JScrollPane scrollPane3;
	JList<String> scorelist;
	JList<String> playerlist;
    static AdminModel model; 
	
    private Graphics jg;
    Game g = new Game();
   
  
    private Color rectColor = new Color(0xf5f5f5);
	private ServerAccess serverAccess;
    
    public DrawSee(AdminModel model) {
    	//super(name);
    	this.model = model;
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
		
		
		
		
		JButton button2 = new JButton("Select");
		button2.setBounds(175, 56, 80, 25);
		getContentPane().add(button2);
		button2.addActionListener(new selectAction());

		JScrollPane scrollPane2 = new JScrollPane();
		scrollPane2.setBounds(50, 100, 90, 350);
		getContentPane().add(scrollPane2);
		
		JList<String> playerlist = new JList<>(new MyListModel());
		scrollPane2.setViewportView(playerlist);
		
		JScrollPane scrollPane3 = new JScrollPane();
		scrollPane3.setBounds(150, 100, 90, 350);
		getContentPane().add(scrollPane3);
		
		JList<String> scorelist = new JList<>(new MyListModel2());
		scrollPane3.setViewportView(scorelist);

        try {           
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }        

      
        jg =  this.getGraphics();
        
        // draw
        //paintComponents(jg);
        gameComponents(gameChoice);
        
    }
    
    class selectAction implements ActionListener{
    	@Override
   	 public void actionPerformed(ActionEvent e) {
    	
    	new ShowGameStateController(DrawSee.this, model).process(gameChoice.getItem(gameChoice.getSelectedIndex()));
    	Choice gameChoice = new Choice();
    	gameChoice.setBounds(45,55, 100, 30);
    	getContentPane().add(gameChoice);
    	
    	
    	g =	AdminModel.getGame(gameChoice.getItem(gameChoice.getSelectedIndex()));   	
   	    paintComponents(jg,g);     ///
   	    }
    }
    
    
	class refreshAction implements ActionListener{
    	@Override
    	 public void actionPerformed(ActionEvent e) {
			new ListGamesController(DrawSee.this, model).process();
    	    //DrawSee drawsee =DrawSee.getGUI();///
    	    DrawSee.getGUI();///
    	    }
    }
    
    class MyListModel extends AbstractListModel<String>{
    	
    	
    	private ArrayList<String> contents=g.getPlayerid();
    	
    	
    		
    
    	 	
    	@Override
    	public String getElementAt(int x){
    		if (x<contents.size())
    			return (String) contents.get(x++);
    		else
    			return null;
    	}
    	@Override
    	public int getSize(){
    		return contents.size();
    	}
    }
    
    class MyListModel2 extends AbstractListModel<String>{
    	
    	private ArrayList<Integer> contents2=g.getScore();
    	
//    	public MyListModel2(Game g){//不确定是不是传game
//    		contents2 =g.getScore();
//    		}
    		
    	
    	@Override
    	public String getElementAt(int x){
    		if (x<contents2.size())
    			return contents2.get(x++).toString();
    		else
    			return null;
    	}
    	
    	@Override
    	public int getSize(){
    		return contents2.size();
    	}
    }
    
    
   public void gameComponents(Choice gamechoice){
	   ArrayList<Game> a = AdminModel.gamelist;
	   for(Game g:a){
		   gamechoice.add(g.getGameid());	   
	   }	        
   }
    
    public void paintComponents(Graphics g,Game game) {
    	
        int size=game.getSize();
        rw=size*40;
       
       
        // set line color
        g.setColor(Color.BLACK);
        
        // draw outer square
        g.drawRect(sx, sy, rw, rw);
        
        
        for(int i = 0; i < size; i ++) {
            // draw inner square
            for(int j=0;j<size;j++){
            g.drawRect(sx + (i * w), sy + (j * w), w, w);}
        }   
            // fill the square
        
        ArrayList<Integer> playerlocation=game.getPlayerlocation();
        for(int i=0;i< playerlocation.size();i++){
        	Color Color = new Color(220+(int)(Math.random()*35),220+(int)(Math.random()*35),220+(int)(Math.random()*35));
			g.setColor(Color);
			g.fillRect((sx+((int)playerlocation.get(i)/10)*40),(sy+((int)playerlocation.get(i)%10)*40), 160, 160);
        }
      

        for(int i = 0; i <=size; i ++){
            for(int j = 0; j < size; j ++) {
                drawString(g, j, i, game);                    
            }
        }
  
}
    private void drawString(Graphics g, int x, int y, Game game) {
    				String[][] globalboard=game.getGlobalboard();
    	             g.setColor(Color.BLACK);
    	             g.setFont(new Font("Arial", 0, 25)); 
    	             g.drawString(globalboard[x][y] + "", sx + (y  * w) + 5, sy + ((x + 1) * w) - 5);
    	      
    	     }

	/** Record the means to communicate with server. */
	public void setServerAccess(ServerAccess access) {
		this.serverAccess = access;
	}
	
	/** Get the server access object. */
	public ServerAccess getServerAccess() {
		return serverAccess;
	}
	
    	 
    
}
