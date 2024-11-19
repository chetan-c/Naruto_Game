import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

class BallGame {
    public static void main(String[] args) {
        JFrame Naruto = new JFrame("Naruto-Game");
        Naruto.setSize(1500, 800);
        Naruto.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int[] position = {200, 200}; // Naruto's position
        

        // Naruto 
        ImageIcon narutoimg = new ImageIcon("c:\\Users\\chetan\\Downloads\\184-1847609_naruto-rasengan-png-naruto-shippuden-naruto-rasengan-transparent-removebg-preview.png");
        JLabel label = new JLabel(narutoimg);
        label.setBounds(position[0], position[1], narutoimg.getIconWidth(), narutoimg.getIconHeight());
        Naruto.add(label);

        // Madara1
        ImageIcon madaraimg1 = new ImageIcon("c:\\Users\\chetan\\Downloads\\download_5-removebg-preview.png");
        JLabel madara1 = new JLabel(madaraimg1);
        madara1.setBounds(1200, 50, madaraimg1.getIconWidth(), madaraimg1.getIconHeight());
        Naruto.add(madara1);

    
        ImageIcon madaraimg2 = new ImageIcon("c:\\Users\\chetan\\Downloads\\images_3-removebg-preview.png");
        JLabel madara2 = new JLabel(madaraimg2);
        madara2.setBounds(50, 600, madaraimg2.getIconWidth(), madaraimg2.getIconHeight());
        Naruto.add(madara2);

        // List to store active Rasengans
        ArrayList<JLabel> rasengans = new ArrayList<>();

        Naruto.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int NarutoMove = e.getKeyCode();

                // Move Naruto
                if (NarutoMove == KeyEvent.VK_UP || NarutoMove == KeyEvent.VK_W) {
                    position[1] -= 10;
                }
                if (NarutoMove == KeyEvent.VK_DOWN || NarutoMove == KeyEvent.VK_S) {
                    position[1] += 10;
                }
                if (NarutoMove == KeyEvent.VK_LEFT || NarutoMove == KeyEvent.VK_A) {
                    position[0] -= 10;
                }
                if (NarutoMove == KeyEvent.VK_RIGHT || NarutoMove == KeyEvent.VK_D) {
                    position[0] += 10;
                }
                label.setBounds(position[0], position[1], label.getWidth(), label.getHeight());
                label.repaint();

               
                if (NarutoMove == KeyEvent.VK_ENTER) {
                    // Rasengan Image
                    ImageIcon rasinganimg = new ImageIcon("c:\\Users\\chetan\\Downloads\\9aba0254f37b92d460e2eb28dc77cd7a75ea79a3_00-removebg-preview.png");
                    JLabel rasengan = new JLabel(rasinganimg);
                    rasengan.setBounds(position[0] + narutoimg.getIconWidth(), position[1] + 50, 50, 50); // Rasengan starts at Naruto's hand
                    Naruto.add(rasengan);
                    rasengans.add(rasengan); 
                    Naruto.repaint();
                }
            }

            public void keyTyped(KeyEvent e) {}
            public void keyReleased(KeyEvent e) {}
        });

        Naruto.setLayout(null);
        Naruto.setFocusable(true);
        Naruto.setFocusTraversalKeysEnabled(false);

      
        Timer rasenganTimer = new Timer(20, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<JLabel> toRemove = new ArrayList<>();
                for (JLabel rasengan : rasengans) {
                   
                    rasengan.setBounds(rasengan.getX() + 5, rasengan.getY(), rasengan.getWidth(), rasengan.getHeight());

                 
                    if (rasengan.getBounds().intersects(madara1.getBounds())) {
                        Naruto.remove(madara1); 
                        Naruto.repaint();

                        
                        Timer reappearMadara1 = new Timer(2000, new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                madara1.setBounds(1200, 50, madaraimg1.getIconWidth(), madaraimg1.getIconHeight());
                                Naruto.add(madara1); 
                                Naruto.repaint();
                            }
                        });
                        reappearMadara1.setRepeats(false); 
                        reappearMadara1.start();
                    }

                   
                    if (rasengan.getBounds().intersects(madara2.getBounds())) {
                        Naruto.remove(madara2); 
                        Naruto.repaint();

                       
                        Timer reappearMadara2 = new Timer(2000, new ActionListener() {
                            public void actionPerformed(ActionEvent evt) {
                                madara2.setBounds(50, 600, madaraimg2.getIconWidth(), madaraimg2.getIconHeight());
                                Naruto.add(madara2);
                                Naruto.repaint();
                            }
                        });
                        reappearMadara2.setRepeats(false); // Run only once
                        reappearMadara2.start();
                    }

                    // Remove Rasengan if it goes off-screen
                    if (rasengan.getX() > Naruto.getWidth()) {
                        toRemove.add(rasengan); // Mark for removal
                    }
                }

                // Remove off-screen Rasengans
                for (JLabel rasengan : toRemove) {
                    Naruto.remove(rasengan);
                    rasengans.remove(rasengan);
                }

                Naruto.repaint();
            }
        });

        
        Timer madaraTimer = new Timer(20, new ActionListener() {
            boolean madara1GoingToNaruto = true;
            boolean madara2GoingToNaruto = true;

            public void actionPerformed(ActionEvent e) {
                
                if (madara1GoingToNaruto) {
                    if (madara1.getX() > position[0]) {
                        madara1.setBounds(madara1.getX() - 2, madara1.getY(), madara1.getWidth(), madara1.getHeight());
                    }
                    if (madara1.getY() < position[1]) {
                        madara1.setBounds(madara1.getX(), madara1.getY() + 1, madara1.getWidth(), madara1.getHeight());
                    } else if (madara1.getY() > position[1]) {
                        madara1.setBounds(madara1.getX(), madara1.getY() - 1, madara1.getWidth(), madara1.getHeight());
                    }

                    
                    if (madara1.getX() <= position[0] && Math.abs(madara1.getY() - position[1]) <= 10) {
                        madara1GoingToNaruto = false;
                    }
                } else {
                    
                    if (madara1.getX() < 1200) {
                        madara1.setBounds(madara1.getX() + 2, madara1.getY(), madara1.getWidth(), madara1.getHeight());
                    }
                    if (madara1.getY() < 50) {
                        madara1.setBounds(madara1.getX(), madara1.getY() + 1, madara1.getWidth(), madara1.getHeight());
                    } else if (madara1.getY() > 50) {
                        madara1.setBounds(madara1.getX(), madara1.getY() - 1, madara1.getWidth(), madara1.getHeight());
                    }

              
                    if (madara1.getX() >= 1200 && madara1.getY() == 50) {
                        madara1GoingToNaruto = true;
                    }
                }

                
                if (madara2GoingToNaruto) {
                    if (madara2.getX() < position[0]) {
                        madara2.setBounds(madara2.getX() + 2, madara2.getY(), madara2.getWidth(), madara2.getHeight());
                    }
                    if (madara2.getY() < position[1]) {
                        madara2.setBounds(madara2.getX(), madara2.getY() + 2, madara2.getWidth(), madara2.getHeight());
                    } else if (madara2.getY() > position[1]) {
                        madara2.setBounds(madara2.getX(), madara2.getY() - 2, madara2.getWidth(), madara2.getHeight());
                    }

                    
                    if (madara2.getX() >= position[0] && madara2.getY() <= position[1]) {
                        madara2GoingToNaruto = false;
                    }
                } else {
                  
                    if (madara2.getX() > 50) {
                        madara2.setBounds(madara2.getX() - 2, madara2.getY(), madara2.getWidth(), madara2.getHeight());
                    }
                    if (madara2.getY() < 600) {
                        madara2.setBounds(madara2.getX(), madara2.getY() + 2, madara2.getWidth(), madara2.getHeight());
                    } else if (madara2.getY() > 600) {
                        madara2.setBounds(madara2.getX(), madara2.getY() - 2, madara2.getWidth(), madara2.getHeight());
                    }

                    //reverse again
                    if (madara2.getX() <= 50 && madara2.getY() == 600) {
                        madara2GoingToNaruto = true;
                    }
                }
            }
        });

        Naruto.setVisible(true);
        madaraTimer.start(); //start moving madara
        rasenganTimer.start(); 
    }
}