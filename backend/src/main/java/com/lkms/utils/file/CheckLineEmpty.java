package com.lkms.utils.file;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CheckLineEmpty {

    public boolean checkLineEmptyOrSpace(String line){
        boolean fullOfSpace = true;
        for (int i=0; i<line.length(); i++){
            if (line.charAt(i) != ' '){
                fullOfSpace = false;
            }
        }
        return !line.isEmpty() && !fullOfSpace;
    }
}
