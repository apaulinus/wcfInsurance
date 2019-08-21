package com.wcf.util;

import java.io.FileInputStream;
import java.io.IOException;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.print.event.PrintJobAdapter;
import javax.print.event.PrintJobEvent;

public class PrintingServiceDemo {
	public void print(String file) throws PrintException, IOException{
		PrintService ps = PrintServiceLookup.lookupDefaultPrintService();
		
		DocPrintJob job = ps.createPrintJob();
		
		job.addPrintJobListener(new PrintJobAdapter() {
			public void printDataTransferCompleted(PrintJobEvent event) {
				System.out.println("data transfer complete");
			}
			
			public void printJobNoMoreEvents(PrintJobEvent event) {
				System.out.println("received no more events");
			}
		});
		
		FileInputStream fis = new FileInputStream(file);
		Doc doc = new SimpleDoc(fis, DocFlavor.INPUT_STREAM.AUTOSENSE, null);
		PrintRequestAttributeSet attribute = new HashPrintRequestAttributeSet();
		attribute.add(new Copies(1));
		
		job.print(doc,  attribute);
	}
}
