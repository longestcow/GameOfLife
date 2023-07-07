import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import javax.swing.DefaultButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class GoL {
	
	
	static Color currCol = Color.white;
	static boolean drawing = true, pressed = false;
	static boolean under = true, over = true;
	
	public static void main(String[] args) {
		
		Color nine = Color.decode("#ffffff"), eight = Color.decode("#e8e8e8"), seven= Color.decode("#d1d1d1"), six = Color.decode("#bbbbbb"), five = Color.decode("#a5a5a5"), four = Color.decode("#8f8f8f"), three = Color.decode("#7a7a7a"), two = Color.decode("#666666"), one = Color.decode("#535353"), zero = Color.decode("#404040"); 
		HashMap<Color, Integer> cton = new HashMap<>();
		cton.put(zero, 0); cton.put(one, 1); cton.put(two, 2); cton.put(three, 3); cton.put(four, 4); cton.put(five, 5); cton.put(six, 6); cton.put(seven, 7); cton.put(eight, 8); cton.put(nine, 9); 
		HashMap<Integer, Color> ntoc = new HashMap<>();
		ntoc.put(0, zero); ntoc.put(1, one); ntoc.put(2, two); ntoc.put(3, three); ntoc.put(4, four); ntoc.put(5, five); ntoc.put(6, six); ntoc.put(7, seven); ntoc.put(8, eight); ntoc.put(9, nine); 

		JFrame fr = new JFrame("omogodot's Game of Life");
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setSize(554,578);
		JButton[] def = new JButton[3600];
		fr.addKeyListener(new KeyAdapter() {
			 public void keyPressed(KeyEvent ev) {
			        System.out.println(ev.getKeyChar());
					switch(ev.getKeyCode()) {
					case KeyEvent.VK_B:
					case KeyEvent.VK_L:
		         		GoL.currCol=nine;
		        		break;
		        	case KeyEvent.VK_E:
		        		GoL.currCol=zero;
		        		break;
		        	case KeyEvent.VK_SPACE:	
	        			GoL.drawing=!GoL.drawing;
		        		break;
		        	case KeyEvent.VK_U:
		        		System.out.println(under);
		        		under=!under;
		        		break;
		        	case KeyEvent.VK_O:
		        		System.out.println(over);
		        		over=!over;
		        		break;
		        	case KeyEvent.VK_ESCAPE:
		        		System.exit(0);
		        		break;
					}

		        
			    }
		});
		JButton[] newbuts = new JButton[3600];
		JButton[] buts = new JButton[3600];

		for(int i = 0; i<3600;i++) {
			JButton but = new JButton();
			but.setBackground(zero);
			but.setName(""+cton.get(zero));
			but.setOpaque(true);
			but.setBorderPainted(false);
			but.setModel(new FixedStateButtonModel());
			but.addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if(drawing) {
			        but.setBackground(currCol);
			        but.setName(""+cton.get(currCol));
			    	}
			    }
			    public void mousePressed(MouseEvent e) {
			    	pressed=true;
			    }
			    public void mouseReleased(MouseEvent e) {
			    	pressed=false;
			    }
			});
			fr.add(buts[i]=but);
			def[i]=but;
		}
		
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setLayout(new GridLayout(60,60));
		fr.setVisible(true);
		fr.setSize(554,577);
		
			while(true) {
				if(drawing) {
		            Point p = MouseInfo.getPointerInfo().getLocation();
		            SwingUtilities.convertPointFromScreen(p, fr);
		            Component mouseOver = fr.findComponentAt(p);
		            fr.requestFocus();
		            if(mouseOver instanceof JButton&&pressed) {
		            mouseOver.setBackground(currCol);
		            mouseOver.setName(""+cton.get(currCol));
		            }   
				}
				else {
					newbuts=buts;
					for(int i = 0; i<3600;i++) {
						if(noOfNeighbours(i, buts) > 3 && over) {
							if(Integer.parseInt(buts[i].getName())!=0) {
								newbuts[i].setName(""+(Integer.parseInt(buts[i].getName())-1));
								newbuts[i].setBackground(ntoc.get(cton.get(buts[i].getBackground())-1));
							}
						}
						else if(noOfNeighbours(i, buts) < 2 && under) {
							if(Integer.parseInt(buts[i].getName())!=0) {
								newbuts[i].setName(""+(Integer.parseInt(buts[i].getName())-1));
								newbuts[i].setBackground(ntoc.get(cton.get(buts[i].getBackground())-1));
							}
						}
						else if(noOfNeighbours(i, buts) == 3) {
							if(Integer.parseInt(buts[i].getName())!=9) {
								newbuts[i].setName(""+(Integer.parseInt(buts[i].getName())+1));
								newbuts[i].setBackground(ntoc.get(cton.get(buts[i].getBackground())+1));
							}
						}
						
												
					}
					
					fr.getContentPane().removeAll();
					
					for(int i =0; i<newbuts.length; i++) {
						fr.getContentPane().add(newbuts[i]);						
					}
					fr.repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					fr.requestFocus();
					buts=newbuts;
					
				}
			}
		
		
	}
	
	
	static int noOfNeighbours(int i, JButton[] buts) {
		int neigh = 0;
		int[] neighbourCheck = new int[] {61,60,59,1};
		
		for(int a : neighbourCheck) {
			if(i-a>=0)
				if(Integer.parseInt(buts[i-a].getName())>4) neigh++;
		}
		for(int a : neighbourCheck) {
			if(i+a<3600)
				if(Integer.parseInt(buts[i+a].getName())>4) neigh++;
		}
		
		return neigh;
	}

}


@SuppressWarnings("serial")
class FixedStateButtonModel extends DefaultButtonModel    {

    @Override
    public boolean isPressed() {
        return false;
    }

    @Override
    public boolean isRollover() {
        return false;
    }

    @Override
    public void setRollover(boolean b) {
        //NOOP
    }

}




