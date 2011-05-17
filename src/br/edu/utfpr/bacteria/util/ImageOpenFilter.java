package br.edu.utfpr.bacteria.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class ImageOpenFilter extends FileFilter {
    public boolean accept(File pathname) {
        if(pathname.isDirectory())
            return true;

        String str = pathname.getName();

        return str.endsWith(".png") || str.endsWith(".PNG") ||
                str.endsWith(".bmp") || str.endsWith(".BMP");
    }

    public String getDescription() {
        return "Arquivos de Imagem (*.bmp, *.png)";
    }
}
