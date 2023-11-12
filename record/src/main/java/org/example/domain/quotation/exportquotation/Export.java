package org.example.domain.quotation.exportquotation;

import org.example.domain.quotation.quotation.servie.Const;
import org.example.global.OutputAndSwitch;
import org.example.standard.utils.util;

public class Export {
    private final OutputAndSwitch outputAndSwitch;

    public Export(OutputAndSwitch outputAndSwitch){
        this.outputAndSwitch = outputAndSwitch;
    }

    public OutputAndSwitch ExportToTxt(){
        util.fileSave(Const.txtFilePath, outputAndSwitch.getQuotations());
        outputAndSwitch.setOutput("파일을 저장합니다.");
        return outputAndSwitch;
    }
}
