/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bootsie;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jpayne
 */
public class DataSample{

   String sampleName;
   ArrayList<Byte> loci = new ArrayList<Byte>();
   DataSample parentSample;

   public DataSample(String n){
      sampleName = n;
   }
   
   public DataSample(String n, ArrayList<Byte> l){
       sampleName = n;
       loci = l;
   }
   
   public DataSample(DataSample d){
       //associate this virtual sample with its parent sample
       sampleName = d.getName() + "-boot";
       parentSample = d;
   }
   
   public DataSample getEquiv(){
       return parentSample;
   }

   public String getName(){
      return sampleName;
   }
   
   public void add(Byte b){
       loci.add(b);
   }

   public ArrayList<Byte> getLoci(){
      return loci;
   }
   
   public Iterator<Byte> iterator(){
       return loci.iterator();
   }
   
   public int size(){
       return loci.size();
   }
   @Override
   public String toString(){
       StringBuilder s = new StringBuilder();
       s.append(sampleName + "\t");
       Iterator<Byte> it = loci.iterator();
       while (it.hasNext()){
           byte b = it.next();
           if (b == -1){
            s.append('.');
           } else {
               s.append(b);
           }
       }
       return s.toString();
   }
   


}
