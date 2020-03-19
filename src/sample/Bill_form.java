package sample;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.awt.FontMetrics;

import static sample.Listfortovar.buyprd;

/**
 *
 * @author mic
 */
public class Bill_form extends javax.swing.JFrame {

    /**
     * Creates new form Bill_form
     */

    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double middleHeight =8.0;
        double headerHeight = 2.0;
        double footerHeight = 2.0;
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(
                0,
                10,
                width,
                height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);

        return pf;
    }

    protected static double convert_CM_To_PPI(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }






    public class BillPrintable implements Printable {
        public double checkAll(){

            double c = 0;
            for (int i = 0; i < buyprd.size(); i++) {
                c=c+buyprd.get(i).getPrice();
            }
            return c;
        }




        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex)
                throws PrinterException
        {

            int result = NO_SUCH_PAGE;
            if (pageIndex == 0) {

                Graphics2D g2d = (Graphics2D) graphics;

                double width = pageFormat.getImageableWidth();

                g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY());

                ////////// code by alqama//////////////

                FontMetrics metrics=g2d.getFontMetrics(new Font("Arial",Font.BOLD,7));
                //    int idLength=metrics.stringWidth("000000");
                //int idLength=metrics.stringWidth("00");
                int idLength=metrics.stringWidth("000");
                int amtLength=metrics.stringWidth("000000");
                int qtyLength=metrics.stringWidth("00000");
                int priceLength=metrics.stringWidth("000000");
                int prodLength=(int)width - idLength - amtLength - qtyLength - priceLength-17;

                //    int idPosition=0;
                //    int productPosition=idPosition + idLength + 2;
                //    int pricePosition=productPosition + prodLength +10;
                //    int qtyPosition=pricePosition + priceLength + 2;
                //    int amtPosition=qtyPosition + qtyLength + 2;

                int productPosition = 0;
                int discountPosition= prodLength+5;
                int pricePosition = discountPosition +idLength+10;
                int qtyPosition=pricePosition + priceLength + 4;
                int amtPosition=qtyPosition + qtyLength;



                try{
                    /*Draw Header*/
                    int y=20;
                    int yShift = 10;
                    int headerRectHeight=15;
                    int headerRectHeighta=40;



                    ///////////////// Product names Get ///////////

                    ///////////////// Product names Get ///////////


                    ///////////////// Product price Get ///////////

                    ///////////////// Product price Get ///////////

                    g2d.setFont(new Font("Monospaced",Font.PLAIN,9));
                    g2d.drawString("-------------------------------------",12,y);y+=yShift;
                    g2d.drawString("          Store Bill Receipt         ",12,y);y+=yShift;
                    g2d.drawString("-------------------------------------",12,y);y+=headerRectHeight;

                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Product Name              T.Price   ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=headerRectHeight;
                    for (int i = 0; i < buyprd.size(); i++){
                        char[] chars = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' ,};
                        String str = new String(chars, 0, 28-buyprd.get(i).getName().length());
                        g2d.drawString(" "+buyprd.get(i).getName()+str+buyprd.get(i).getPrice()+"  ",10,y);y+=yShift;
                    }
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString(" Total amount: "+checkAll()+"               ",10,y);y+=yShift;
                    g2d.drawString("-------------------------------------",10,y);y+=yShift;
                    g2d.drawString("            BoyOrelStudio            ",10,y);y+=yShift;
                    g2d.drawString("              0668912465             ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;
                    g2d.drawString("        It's revolution, Johny!      ",10,y);y+=yShift;
                    g2d.drawString("*************************************",10,y);y+=yShift;





//            g2d.setFont(new Font("Monospaced",Font.BOLD,10));
//            g2d.drawString("Customer Shopping Invoice", 30,y);y+=yShift;


                }
                catch(Exception r){
                    r.printStackTrace();
                }

                result = PAGE_EXISTS;
            }
            return result;
        }
    }



    public void startPrint() {//GEN-FIRST:event_jButton1ActionPerformed

        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


}