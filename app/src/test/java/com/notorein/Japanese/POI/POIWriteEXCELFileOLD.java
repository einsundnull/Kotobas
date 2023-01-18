package com.notorein.Japanese.POI;

import com.notorein.BasicAppII.Arrays;
import com.notorein.BasicAppII.Parameter.ParameterEXCEL;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

//import com.itextpdf.text.BaseColor;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Font;
//import com.itextpdf.text.FontFactory;
//import com.itextpdf.text.PageSize;
//import com.itextpdf.text.Phrase;
//import com.itextpdf.text.log.SysoCounter;
//import com.itextpdf.text.pdf.BaseFont;
//import com.itextpdf.text.pdf.PdfPCell;
//import com.itextpdf.text.pdf.PdfPTable;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import cwindow.CWindow;
//import cwindow.MainWindow;
//import strings.LoadedVerbs;

public class POIWriteEXCELFileOLD {

	public static String tableName = "";
	public static String folderName = "Conjugations";
	private static String folderDate;
	public static int substractor = 6;
	public static int whiteColumnIndex;
	public static XSSFWorkbook workbook;
	public static File excelFileToWrite;
	public static File fileSource;
	public static float[] numberOfCells;
	public static float[] cellWidths;
	public static String filePath;
//	public static File fileDestinationEXCEL;
//	public static File fileGE;
//	public static File fileRUIT;
//	public static File fileHEADER;
	private static int rows;
	private static int cells;
	private static float[] columnWidths;
	private static XSSFSheet sheet;
	private final int sheetIndex;
	private final int columnIndex;
	private final int cellIndex;

	public POIWriteEXCELFileOLD(File file, int sheetIndex, int columnIndex, int cellIndex) {
		this.excelFileToWrite = file;
		this.sheetIndex = sheetIndex;
		this.columnIndex = columnIndex;
		this.cellIndex = cellIndex;
	}

	public static void writeToExcelTable() {

		Thread thread = new Thread() {

//			private BaseColor baseColor = new BaseColor(235, 233, 228);

			@Override
			public void run() {
				try {
//					ClassLoader classLoader = CWindow.class.getClassLoader();
					InputStream    inputStream = new FileInputStream(excelFileToWrite);
					int size = 0;
					byte[] bucket = null;
					try {
						size = inputStream.available();
						bucket = new byte[size];
						inputStream.read(bucket);
					} catch (IOException e2) {
						e2.printStackTrace();

					}
					addaptTableName();
					FileOutputStream outputStream = null;

					try {
						outputStream = new FileOutputStream(excelFileToWrite);
					} catch (FileNotFoundException e2) {
						e2.printStackTrace();
					}
					try {
						outputStream.write(bucket);
					} catch (IOException e) {
						e.printStackTrace();
					}

					String filePath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + folderName;
					new File(filePath).mkdir();
					inputStream = new FileInputStream(excelFileToWrite);
					XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
					sheet = workbook.getSheetAt(ParameterEXCEL.sheetIndex);
					rows = Arrays.loadedCardsAsList.size();
					cells = Arrays.lessonsTemplateCardMetaInfoArrayMinLength;
//					columnWidths = new float[cells];
					for (int i = 0; i < rows; i++) {
						Row row = sheet.getRow(i);
						for (int n = 0; n < cells; n++) {
//							System.err.println("i " + i + "  n " + n);
							Cell cell = row.getCell(n);
							try {
								cell.setCellValue(Arrays.loadedCardsAsList.get(i).get(n));
							} catch (Exception e) {
								cell.setCellValue("");
							}

						}
					}
					for (int n = 0; n < cells; n++) {
						columnWidths[n] = sheet.getColumnWidth(n);
					}
					inputStream.close();
					outputStream = new FileOutputStream(excelFileToWrite);
					workbook.write(outputStream);
					outputStream.close();
					workbook.close();

					// We will create output PDF document objects at this point
					storeAsPDFLogic();


//					if (MainWindow.storeAsTEXT) {
//						FileWriter wrt = new FileWriter(filePath + File.separator + POIWriteEXCELFile.tableName + ".txt");
//						BufferedWriter bf = new BufferedWriter(wrt);
//						bf.write(FixedStrings.completeText);
//						bf.close();
//					}
//					if (!MainWindow.storeAsEXCEL) {
//						excelFileToWrite.delete();
//					}

				} catch (IOException e /* DocumentException e*/) {
					e.printStackTrace();
				}
//				LoadedVerbs.printArray.clear();
			}
		};
		thread.setDaemon(true);
		thread.start();
	}

	private static void storeAsPDFLogic() {
//		if (MainWindow.storeAsPDF) {
//			FileInputStream document = new FileInputStream(excelFileToWrite);
//			Document pdf = new Document(PageSize.A4, 10, 10, 50, 10);
//			PdfWriter.getInstance(pdf, new FileOutputStream(filePath + File.separator + POIWriteEXCELFile.tableName + ".pdf"));
//			pdf.open();
//			int n = 0;
//			PdfPTable table = new PdfPTable(columnWidths);
//			PdfPCell pdfCell;
//			for (int i = 0; i < rows - 1; i++) {
//				for (n = 0; n < cells; n++) {
//					Cell cell = sheet.getRow(i).getCell(n);
//					FontFactory.defaultEmbedding = true;
//					Font fontH1 = null;
//					boolean header = false;
//					boolean ge = false;
//					boolean ru = false;
//					boolean lt = false;
//					URL urlI = null;
//					URL urlII = null;
//					if (i == 0 || i == 3 || i == 12 || i == 21 || i == 30 || i == 39) {
//						header = true;
//					}
//					if (header) {
//						if (FixedStrings.caseIndexLang == 2) {
//							ru = true;
//						}
//						if (FixedStrings.caseIndexLang == 3) {
//							ge = true;
//						} else if (!ge && !ru) {
//							lt = true;
//						}
//						if (ge) {
//							try {
//								// It is okay to use the deprecated method since the recommended way to convert
//								// it to URI is used one line below
//								URL res = POIWriteEXCELFile.class.getResource("/shemes/bpg_glaho_sylfaen.ttf");
//								File file = Paths.get(res.toURI()).toFile();
//								String fontPathII = file.getPath();
//								BaseFont bfII = BaseFont.createFont(fontPathII, BaseFont.IDENTITY_H, true);
//								fontH1 = new Font(bfII, 8, Font.NORMAL);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//						if (ru || lt) {
//							try {
//								URL res = POIWriteEXCELFile.class.getResource("/shemes/ClearSans-Bold.ttf");
//								File file = Paths.get(res.toURI()).toFile();
//								String fontPathII = file.getPath();
//								BaseFont bfII = BaseFont.createFont(fontPathII, BaseFont.IDENTITY_H, true);
//								fontH1 = new Font(bfII, 8, Font.BOLD);
//							} catch (Exception e) {
//								e.printStackTrace();
//							}
//						}
//					}
//					if (!header) {
//						try {
//							// It is okay to use the deprecated method since the recommended way to convert
//							// it to URI is used one line below
//							URL res = POIWriteEXCELFile.class.getResource("/shemes/ClearSans-Medium.ttf");
//							File file = Paths.get(res.toURI()).toFile();
//							String fontPathII = file.getPath();
//							BaseFont bfII = BaseFont.createFont(fontPathII, BaseFont.IDENTITY_H, true);
//							fontH1 = new Font(bfII, 8, Font.NORMAL);
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//					try {
//						String check = cell.getStringCellValue();
//						if(check.isEmpty()) {
//							// That is necessary since if the String is empty the table will not printed correctly
//							check = " ";
//						}
//						Phrase phrase = new Phrase(check, fontH1);
//						pdfCell = new PdfPCell();
//						pdfCell.setPhrase(phrase);
//						pdfCell.setNoWrap(true);
//						pdfCell.setBorder(0);
//						pdfCell.setPaddingLeft(10);
//						pdfCell.setPaddingBottom(2);
//						if (i == 0 || i == 1) {
//							pdfCell.setBackgroundColor(baseColor);
//						}
//						if (n != whiteColumnIndex) {
//							if (i != 2 && i != 11 && i != 20 && i != 29 && i != 38) {
//								pdfCell.setBackgroundColor(baseColor);
//							}
//						}
//						table.addCell(pdfCell);
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				}
//			}
//			pdf.add(table);
//			pdf.close();
//			document.close();
//		}
//		System.gc();
	}

	protected static void addaptTableName() {
//		// Here I decide whether the translation or the German verb is first displayed as the name of the PDF/EXCEL file
//		if (MainWindow.fileNameGermanFirst) {
//			POIWriteEXCELFile.tableName = MainWindow.refPron + " " + ConjugateVerbs.preSyl + MainWindow.input[0] + " - " + MainWindow.input[1];
//		} else {
//			POIWriteEXCELFile.tableName = MainWindow.input[1] + " - " + MainWindow.refPron + " " + ConjugateVerbs.preSyl + MainWindow.input[0];
//		}
//
	}

	protected static void storeAsPDF(String filePath2) throws FileNotFoundException/*, DocumentException*/ {


	}

}
