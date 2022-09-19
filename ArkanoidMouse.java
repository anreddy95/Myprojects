import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class ArkanoidMouse extends Applet implements Runnable,MouseMotionListener
{
 	Thread t;
 	boolean flag=false;
	boolean flag1=false;
 	int w,h,x,y,bx,by;
 	int chgX,chgY;
	int length,breadth;
	Random rnd;
	Color c;
	int dia;
	int over;
	int score;
	int speed,start;

	public void init()
	{
		addMouseMotionListener(this);
		c = Color.orange;
		w = getWidth();
		h = getHeight();
		by=getHeight();
		by=by-60;
		bx=getWidth();
		bx=bx/2-50;
		setBackground(Color.black);
		//setForeground(c);
		dia=15;
		x=100;
		y=100;	
		chgX=5;
		chgY=5;
		length=200;
		breadth=10;
		over=0;
		score=0;
		speed=15;
		start=0;
	}

	public void start()
	{
		t = new Thread(this);
		flag = true;
		t.start();
	}

	public void stop()
	{
		flag = false;
		t = null;
	}

	public void run()
	{
		while(flag)
		{
			if(x<=0||x>=(w-dia))
			{
				chgX = chgX * -1;
			}
			if(y<=0)
			{
				chgY=chgY*-1;
			}
			if((y>=by-dia && x>bx && x<bx+length) && over==0)
			{
				chgY = chgY * -1;
				score++;
				start=1;
				if(score%4==0&&start==1)
				{
					if(speed>5)
					{
						speed=speed-1;
						length-=5;
					}
				}
			}
			if((x+dia==bx || x==bx+length) && (y>=by && y<=by+breadth)&& over==0)
			{
				chgX=chgX*-1;	
			}
			if(y>=(h-dia))
			{
				over=1;
			}
			x=x+chgX;
			y=y+chgY;
			repaint();
			try
			{
				t.sleep(speed);
			}
			catch(InterruptedException e)
			{
			}

		}
	}

	public void paint(Graphics g)
	{
		if(over==0)
		{
			g.setColor(c);
			g.fillOval(x,y,dia, dia);
			g.setColor(Color.red);
			g.drawRect(bx,by,length,breadth);
			g.fillRect(bx,by,length,breadth);
			g.drawString("SCORE",getWidth()-200,100);
			g.drawString(Integer.toString(score),getWidth()-180,130);
		}
		else if(over==1)
		{
			g.setColor(Color.red);
			g.drawString("GAME OVER",300,300);
			g.drawString("SCORE",600,300);
			g.drawString(Integer.toString(score),600,320);
		}
	}
	public void mouseMoved(MouseEvent e)
	{
		int tempx=e.getX();
		if(tempx<=getWidth()-length)
		{
			bx=tempx;
		}
		repaint();
	}
	public void mouseDragged(MouseEvent e)
	{
	}
	
}