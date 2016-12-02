package adminview;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 定义一个继承自JPanel的类，重写它的paint方法 *
 */
class MyPanel1 extends JPanel
{
	private int x = 100;
	private int y = 200;
	private Image image;	//图像缓冲
	private Graphics og;
	
	public void display()
	{
		if(og == null)
		{
			//JPanel继承自Component类，可以使用它的方法createImage创建一幅和JPanel大小相同的图形缓冲
			//然后用它Image接口的方法获得绘图对像
			image = this.createImage(this.getWidth(),this.getHeight());
			if(image != null)og = image.getGraphics();
		}
		
		if(og != null)
		{
			//调用的super.paint(g),让父类做一些事前的工作，如刷新屏幕
			super.paint(og);
			
//			og.setColor(Color.getHSBColor(60, 60, 60));				//设置画图的颜色
//			og.fill3DRect(x-100, y, 100, 100, true);//绘图			
//			og.setColor(Color.getHSBColor(100, 100, 100));				//设置画图的颜色
//			og.fill3DRect(x, y, 100, 100, true);//绘图
//			og.setColor(Color.getHSBColor(110, 110, 110));				//设置画图的颜色
//			og.fill3DRect(x+100, y, 100, 100, true);//绘图
//			og.setColor(Color.getHSBColor(120, 120, 120));				//设置画图的颜色
//			og.fill3DRect(x+200, y, 100, 100, true);//绘图
			for(int i=1;i<6;i++){
			Color rectColor = new Color(220+(int)(Math.random()*35),220+(int)(Math.random()*35),220+(int)(Math.random()*35));
			og.setColor(rectColor);
			og.fill3DRect(x+(i-2)*100, y, 100, 100, true);}//绘图
			
			//this.paint(this.getGraphics());
		}
		//重绘JPanel
		this.repaint();
	}
	
	/**
	 * repaint方法会调用paint方法，并自动获得Graphics对像
	 * 然后可以用该对像进行2D画图
	 * 注：该方法是重写了JPanel的paint方法
	 */
	public void paint(Graphics g)
	{
		g.drawImage(image, 0, 0, this);	
	}
}

public class DrawBoard
{
	public static void main(String[] args)
	{
		JFrame  jf = new JFrame();
		MyPanel1 jp = new MyPanel1();
		
		jf.setBounds(200, 200, 500, 500);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(jp);
		jf.setVisible(true);
		
	
			jp.display();
			
			try
			{
				Thread.sleep(300);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	
}
