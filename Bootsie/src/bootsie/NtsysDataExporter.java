/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package bootsie;

import java.io.File;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author jpayne
 */
public class NtsysDataExporter extends DataExporter {

   private static String fileExtention = ".txt";

   private static String header = "";
   private static String nullChar = "9";
   private static String delimitChar = "\t";

   @Override
   public void dataExport(File file, ArrayList<PopulationMatrixModel> data, Boolean combine) {
      //build export string via stringbuilder
      if (combine) {
         StringBuilder export = new StringBuilder(header);
         Iterator<PopulationMatrixModel> it = data.iterator();
         while (it.hasNext()){
            export.append(generateString(it.next()));
         }
         BootsieApp.getApplication().exportFile(file, export);
      } else {
         ArrayList<StringBuilder> exports = new ArrayList<StringBuilder>();
         Iterator<PopulationMatrixModel> it = data.iterator();
         while(it.hasNext()){
            StringBuilder export = new StringBuilder(header);
            export.append(generateString(it.next()));
            exports.add(export);
         }
         ArrayList<File> files = new ArrayList<File>();
         BootsieApp.getApplication().exportFiles(files, exports);
      }
      
      
      
   }

   @Override
    public StringBuilder generateString(PopulationMatrixModel data) {
        StringBuilder export = new StringBuilder();
        Iterator<DataSample> it = data.iterator();
        int sample = 0;
        export.append("1 ").append(data.getSize()).append(" ").append(data.getLength()).append(" 1 9\n");
        while (it.hasNext()) {
            sample++;
            DataSample s = it.next();
            ArrayList<Byte> loci = s.getLoci();
            Iterator<Byte> i = loci.iterator();
            export.append(sample).append(delimitChar);
            while (i.hasNext()) {
                Byte b = i.next();
                if (b != 0 && b != 1) {
                    export.append(nullChar);
                } else {
                    export.append(b.toString());
                }
                export.append(delimitChar);
            }
            export.append("\n");
        }
        return export;
   }

   @Override
   public String getFileExtention() {
      return fileExtention;
   }
   
}
