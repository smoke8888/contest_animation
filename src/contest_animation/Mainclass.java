package contest_animation;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.AttributedCharacterIterator;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Mainclass {
	public static void main(String[] args) {
		
		
		String str;
		String[] in = new String[5000];
		File input = new File(System.getProperty("user.dir"),"animations/10.txt");
        try
        {
            char[] set_char = new char[50000];
            FileReader input_reader = new FileReader(input);
            while((input_reader.read(set_char)) != -1) {}
            str = String.valueOf(set_char);
            input_reader.close();
            in = str.split("\\u0000| |\n");
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
		
        
		JFrame w=new JFrame("Окно с изображением");
		w.setSize(Integer.valueOf(in[0]), Integer.valueOf(in[1]));
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
		w.setLayout(new BorderLayout(1,1));
		
		
		
		Canvas canv=new Canvas(in);
        w.add(canv);
        w.setVisible(true);
	}
}


class Canvas extends JComponent{
  private String[] input;

public Canvas(String[] a) {
	  this.input = a;
  }
	 
  public void paintComponent(Graphics g){
	super.paintComponents(g);		
	Graphics2D g2d=(Graphics2D)g;
 
	int i = 0;
	int x_width = 0;
	int y_height = 0;
	int x_coord = 0;
	int y_coord = 0; 
	int rad = 0;
	
	for(String val : input) {
		
		if (val.equals("rectangle")) {
			if (((i+13) <= input.length-1) && (input[i+13].equals("scale"))) {
				x_width = Math.round(Float.parseFloat(input[i+3]) * Float.parseFloat(input[i+14]));
				y_height = Math.round(Float.parseFloat(input[i+4]) * Float.parseFloat(input[i+14]));
			}
			else {
				x_width = Math.round(Float.parseFloat(input[i+3]));
				y_height = Math.round(Float.parseFloat(input[i+4]));
			}
			
			if ((Integer.parseInt(input[i+7]) > 0) && (input[i+8].equals("move"))){	
				
				if (((i+12) <= input.length-1) && (input[i+12].equals("cycle"))) {
					int min_x = Math.min(Math.round(Float.parseFloat(input[i+1])), Math.round(Float.parseFloat(input[i+9])));
					int min_y = Math.min(Math.round(Float.parseFloat(input[i+2])), Math.round(Float.parseFloat(input[i+10])));
					x_coord = Math.abs(Math.round(Float.parseFloat(input[i+1])) - Math.round(Float.parseFloat(input[i+9])))/2 + min_x - x_width/2;
					y_coord = Math.abs(Math.round(Float.parseFloat(input[i+2])) - Math.round(Float.parseFloat(input[i+10])))/2 + min_y - y_height/2;
				}
				else {
					x_coord = Math.round(Float.parseFloat(input[i+9])) - x_width/2;
					y_coord = Math.round(Float.parseFloat(input[i+10])) - y_height/2;
				}
			}
			else {
				x_coord = Math.round(Float.parseFloat(input[i+1])) - x_width/2;
				y_coord = Math.round(Float.parseFloat(input[i+2])) - y_height/2;
			}		
			g2d.drawRect(x_coord,y_coord,x_width,y_height);
		}
		
		if (val.equals("circle")) {
			if (((i+11) <= input.length-1) && (input[i+11].equals("scale"))) {
				rad = Math.round(Float.parseFloat(input[i+3])*Float.parseFloat(input[i+12])) ;
			}
			else {rad = Math.round(Float.parseFloat(input[i+3]));}
			
			if ((Integer.parseInt(input[i+5]) > 0) && (input[i+6].equals("move"))){	
				
				if (((i+10) <= input.length-1) && (input[i+10].equals("cycle"))) {
					int min_x = Math.min(Math.round(Float.parseFloat(input[i+1])), Math.round(Float.parseFloat(input[i+7])));
					int min_y = Math.min(Math.round(Float.parseFloat(input[i+2])), Math.round(Float.parseFloat(input[i+8])));
					x_coord = Math.abs(Math.round(Float.parseFloat(input[i+1])) - Math.round(Float.parseFloat(input[i+7])))/2 + min_x - rad;
					y_coord = Math.abs(Math.round(Float.parseFloat(input[i+2])) - Math.round(Float.parseFloat(input[i+8])))/2 + min_y - rad;
				}
				else {
					x_coord = Math.round(Float.parseFloat(input[i+7])) - rad;
					y_coord = Math.round(Float.parseFloat(input[i+8])) - rad;
				}
			}
			else {
				x_coord = Math.round(Float.parseFloat(input[i+1])) - rad;
				y_coord = Math.round(Float.parseFloat(input[i+2])) - rad;
			}
					
			g2d.drawOval(x_coord, y_coord, 2*rad, 2*rad);
		}
		
		++i;
	}
	
	
	
	

	super.repaint();
  }	
}