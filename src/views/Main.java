package views;
import java.io.File;
import java.io.IOException;
import java.util.List;

import abstractClasses.Search;
import bean.SearchResultBean;
import bean.SearchResultDetailBean;

import com.cloudgarden.resource.SWTResourceManager;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import util.Const;
import util.SearchExcel;
import util.SearchText;
import util.SearchWord;
import util.Utilities;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class Main extends org.eclipse.swt.widgets.Composite {
	private Menu menuBar;
	private MenuItem helpMenuItem;
	private Text directoryPathTextInput;
	private Composite basicDisplayComposite;
	private Composite bunyukDisplayComposite;
	private CTabFolder cTabFolder;
	private Label countResult;
	private Label countResultFiles;
	private Label countFileForSearch;
	private TableColumn fileNameBunyuk;
	private CTabItem bunyukDisplayCTabItem;
	private CTabItem basicDisplayCTabItem;
	private Label guideToOpenAFile;
	private TableColumn contentText;
	private TableColumn row;
	private TableColumn col;
	private TableColumn rowBunyuk;
	private TableColumn colBunyuk;
	private TableColumn startTimeBunyuk;
	private TableColumn contentTextBunyuk;
	private TableColumn sheetName;
	private TableColumn fileName;
	private Table resultTable1;
	private Table resultTable2;
	private Button executeSearchButton;
	private Button[] fileTypeRadio = new Button[3];
	private Group group3;
	private Text textForSearchingTextInput;
	private Group group2;
	private Button directoryPathButton;
	private Group group1;
	private MenuItem aboutMenuItem;
	private Menu helpMenu;

	private String searchingPath = "";
	{
		SWTResourceManager.registerResourceUser(this);
	}
	
	
	public void initGUI() {
		try {
			{
				this.setSize(498, 669);
				// 바탕화면 배경색
				this.setBackground(Const.BACKGROUND_COLOR);
				this.setBackgroundImage(SWTResourceManager.getImage("wallpaper.jpg"));
				this.setBackgroundMode(SWT.INHERIT_DEFAULT);

				{
					// 검색 키워드 입력
					group2 = new Group(this, SWT.SHADOW_OUT);
					GridLayout group2Layout = new GridLayout();
					group2Layout.makeColumnsEqualWidth = true;
					group2.setLayout(group2Layout);
					group2.setText(Const.GROUP_TEXT_FOR_SEARCHING_TITLE);
					group2.setBounds(12, 12, 474, 47);
					group2.setBackground(Const.BACKGROUND_COLOR);
					{
						textForSearchingTextInput = new Text(group2, SWT.NONE);
						GridData textForSearchingTextInputLData = new GridData();
						textForSearchingTextInputLData.widthHint = 451;
						textForSearchingTextInputLData.heightHint = 22;
						textForSearchingTextInput.setLayoutData(textForSearchingTextInputLData);
						textForSearchingTextInput.setText("");
						textForSearchingTextInput.addListener(SWT.DefaultSelection, new Listener() {
							@Override
							public void handleEvent(Event e) {
								search();
							}
						});
					}
					group2.pack();
				}
				{
					// 검색대상 폴더 지정
					group1 = new Group(this, SWT.SHADOW_OUT);
					RowLayout group1Layout = new RowLayout(org.eclipse.swt.SWT.HORIZONTAL);
					group1.setLayout(group1Layout);
					group1.setText(Const.GROUP_DIRECTORY_PATH_TITLE);
					group1.setBounds(12, 69, 469, 54);
					group1.setBackground(Const.BACKGROUND_COLOR);
					{
						directoryPathTextInput = new Text(group1, SWT.SINGLE | SWT.READ_ONLY);
						RowData directoryPathTextInputLData = new RowData();
						directoryPathTextInputLData.width = 368;
						directoryPathTextInputLData.height = 25;
						directoryPathTextInput.setLayoutData(directoryPathTextInputLData);
						
						// 지난번 실행 때 검색한 폴더가 있다면 그걸 다시 표시
						String zenkaiSearchKeyword = Utilities.inputSaveFile();
						if (zenkaiSearchKeyword != null && ! "".equals(zenkaiSearchKeyword)) {
							directoryPathTextInput.setText(zenkaiSearchKeyword);
						} else {
							directoryPathTextInput.setText(new File(Const.DEFAULT_PATH).getCanonicalPath());
						}
						
						directoryPathTextInput.setBackground(SWTResourceManager.getColor(223, 223, 223));
						directoryPathTextInput.setEditable(false);
					}
					{
						directoryPathButton = new Button(group1, SWT.PUSH | SWT.CENTER);
						RowData directoryPathButtonLData = new RowData();
						directoryPathButton.setLayoutData(directoryPathButtonLData);
						directoryPathButton.setText(Const.GROUP_DIRECTORY_PATH_SEARCH_BUTTON);
						directoryPathButton.addListener(SWT.Selection, new Listener() {
							
							// 폴더 선택 버튼 클릭시
							@Override
							public void handleEvent(Event event) {
								DirectoryDialog dialog = new DirectoryDialog(getShell());
								dialog.setMessage(Const.MESSAGE_FOLDER_SELECT);
								// 현재 선택 폴더 표시
								dialog.setFilterPath(directoryPathTextInput.getText());
								
								String saveTarget = dialog.open();
								if(saveTarget != null)
								{
									directoryPathTextInput.setText(saveTarget);
									// 検索フォルダを保存
									Utilities.outputSaveFile(directoryPathTextInput.getText());
								}
							}
						});
					}
					group1.pack();
				}
				{
					// 파일종류선택
					group3 = new Group(this, SWT.SHADOW_OUT);

					group3.setLayout(null);
					group3.setText(Const.GROUP_FILE_TYPE_TITLE);
					group3.setBounds(13, 126, 337, 68);
					group3.setBackground(Const.BACKGROUND_COLOR);

					{
						fileTypeRadio[0] = new Button(group3, SWT.RADIO | SWT.LEFT);
						fileTypeRadio[0].setText(Const.GROUP_FILE_TYPE_RADIO_TEXT);
						fileTypeRadio[0].setBackground(Const.BACKGROUND_COLOR);
						fileTypeRadio[0].setSelection(true);
						fileTypeRadio[0].setBounds(8, 20, 139, 16);
					}
					{
						fileTypeRadio[1] = new Button(group3, SWT.RADIO | SWT.LEFT);
						fileTypeRadio[1].setText(Const.GROUP_FILE_TYPE_RADIO_EXCEL);
						fileTypeRadio[1].setBackground(Const.BACKGROUND_COLOR);
						fileTypeRadio[1].setBounds(8, 35, 145, 30);
					}
					{
						fileTypeRadio[2] = new Button(group3, SWT.RADIO | SWT.LEFT);
						fileTypeRadio[2].setText(Const.GROUP_FILE_TYPE_RADIO_WORD);
						fileTypeRadio[2].setBackground(Const.BACKGROUND_COLOR);
						fileTypeRadio[2].setBounds(165, 35, 165, 30);
					}
					group3.pack(false);
				}
				{
					// 검색실행 버튼
					executeSearchButton = new Button(this, SWT.PUSH | SWT.CENTER);
					executeSearchButton.setText(Const.EXECUTE_SEARCH_BUTTON);
					executeSearchButton.setBounds(362, 141, 119, 47);
					executeSearchButton.addListener(SWT.Selection, new Listener() {
						
						@Override
						public void handleEvent(Event event) {
							search();
						}
					});
				}
				{
					guideToOpenAFile = new Label(this, SWT.NONE);
					guideToOpenAFile.setText(Const.GUIDE_TO_OPEN_FILE);
					guideToOpenAFile.setBounds(12, 649, 446, 22);
				}
				{
					countFileForSearch = new Label(this, SWT.NONE);
					countFileForSearch.setText(Const.COUNT_FILE_FOR_SEARCH + "0");
					countFileForSearch.setBounds(13, 200, 143, 16);
				}
				{
					countResultFiles = new Label(this, SWT.NONE);
					countResultFiles.setText(Const.COUNT_RESULT_FILE + "0");
					countResultFiles.setBounds(161, 200, 190, 15);
				}
				{
					countResult = new Label(this, SWT.NONE);
					countResult.setText(Const.COUNT_RESULT + "0");
					countResult.setBounds(356, 200, 136, 15);
				}
				// 테이블 표시 라디오버튼
				{
					cTabFolder = new CTabFolder(this, SWT.NONE);
					{
						basicDisplayCTabItem = new CTabItem(cTabFolder, SWT.NONE);
						basicDisplayCTabItem.setText(Const.TAB_ITEM_BASIC_DISPLAY);
						{
							basicDisplayComposite = new Composite(cTabFolder, SWT.NONE);
							GridLayout composite1Layout = new GridLayout();
							composite1Layout.makeColumnsEqualWidth = true;
							basicDisplayComposite.setLayout(composite1Layout);
							basicDisplayCTabItem.setControl(basicDisplayComposite);
							{
								resultTable1 = new Table(basicDisplayComposite, SWT.NONE);
								resultTable1.setHeaderVisible(true);
								GridData resultTableLData = new GridData();
								resultTableLData.widthHint = 461;
								resultTableLData.heightHint = 377;
								
								resultTable1.setLayoutData(resultTableLData);
								resultTable1.setBackgroundImage(SWTResourceManager.getImage("usagi.jpg"));
								{
									fileName = new TableColumn(resultTable1, SWT.NONE);
									fileName.setText(Const.TABLE_COLUMN_NAME_FILE_NAME);
									fileName.setWidth(127);
								}
								{
									sheetName = new TableColumn(resultTable1, SWT.NONE);
									sheetName.setText(Const.TABLE_COLUMN_NAME_SHEET_NAME);
									sheetName.setWidth(60);
								}
								{
									row = new TableColumn(resultTable1, SWT.NONE);
									row.setText(Const.TABLE_COLUMN_NAME_ROW);
									row.setWidth(45);
								}
								{
									col = new TableColumn(resultTable1, SWT.NONE);
									col.setText(Const.TABLE_COLUMN_NAME_COL);
									col.setWidth(38);
								}
								{
									contentText = new TableColumn(resultTable1, SWT.NONE);
									contentText.setText(Const.TABLE_COLUMN_NAME_CONTENT_TEXT);
									contentText.setWidth(190);
								}
								
								// 테이블 더블클릭
								resultTable1.addListener(SWT.MouseDoubleClick, new Listener() {
									@Override
									public void handleEvent(Event e) {
										if (e.x > fileName.getWidth()) {
											return;
										}
										Point pt = new Point(20, e.y);
										TableItem selection = resultTable1.getItem(pt);
										if (selection != null) {
											String selectedId = selection.getText();	// ID취득
											
											try {
												// 파일 열기
												new ProcessBuilder("cmd", "/c", searchingPath + "/" + selectedId).start();
											} catch (IOException e1) {
												Utilities.showErrorMsg(getShell(), e1.getMessage());
											}
										}
									}
								});
							}
						}

						bunyukDisplayCTabItem = new CTabItem(cTabFolder, SWT.NONE);
						bunyukDisplayCTabItem.setText(Const.TAB_ITEM_BUNYUK_DISPLAY);
						{
							bunyukDisplayComposite = new Composite(cTabFolder, SWT.NONE);
							GridLayout composite2Layout = new GridLayout();
							composite2Layout.makeColumnsEqualWidth = true;
							bunyukDisplayComposite.setLayout(composite2Layout);
							bunyukDisplayCTabItem.setControl(bunyukDisplayComposite);
						}
						{
							resultTable2 = new Table(bunyukDisplayComposite, SWT.NONE);
							resultTable2.setHeaderVisible(true);
							GridData resultTableLData2 = new GridData();
							resultTableLData2.widthHint = 461;
							resultTableLData2.heightHint = 377;
							
							resultTable2.setLayoutData(resultTableLData2);
							resultTable2.setBackgroundImage(SWTResourceManager.getImage("usagi.jpg"));
							{
								fileNameBunyuk = new TableColumn(resultTable2, SWT.NONE);
								fileNameBunyuk.setText(Const.TABLE_COLUMN_NAME_FILE_NAME);
								fileNameBunyuk.setWidth(127);
							}
							{
								rowBunyuk = new TableColumn(resultTable2, SWT.NONE);
								rowBunyuk.setText(Const.TABLE_COLUMN_NAME_JAMAK_NUM);
								rowBunyuk.setWidth(45);
							}
							{
								colBunyuk = new TableColumn(resultTable2, SWT.NONE);
								colBunyuk.setText(Const.TABLE_COLUMN_NAME_TRACK);
								colBunyuk.setWidth(44);
							}
							{
								startTimeBunyuk = new TableColumn(resultTable2, SWT.NONE);
								startTimeBunyuk.setText(Const.TABLE_COLUMN_NAME_START_TIME);
								startTimeBunyuk.setWidth(97);
							}
							{
								contentTextBunyuk = new TableColumn(resultTable2, SWT.NONE);
								contentTextBunyuk.setText(Const.TABLE_COLUMN_NAME_CONTENT_TEXT);
								contentTextBunyuk.setWidth(154);
							}
							
							// 테이블 더블클릭
							resultTable2.addListener(SWT.MouseDoubleClick, new Listener() {
								@Override
								public void handleEvent(Event e) {
									if (e.x > fileName.getWidth()) {
										return;
									}
									Point pt = new Point(20, e.y);
									TableItem selection = resultTable2.getItem(pt);
									if (selection != null) {
										String selectedId = selection.getText();	// ID취득
										
										try {
											// 파일 열기
											new ProcessBuilder("cmd", "/c", searchingPath + "/" + selectedId).start();
										} catch (IOException e1) {
											Utilities.showErrorMsg(getShell(), e1.getMessage());
										}
									}
								}
							});
						}

						cTabFolder.setBounds(2, 219, 492, 428);
						cTabFolder.setSelection(0);
						cTabFolder.setSelectionBackground(SWTResourceManager.getColor(255, 200, 200));
						cTabFolder.setSimple(false);
					}
				}

				// 메뉴바 만들기
				setMenuBar();
			}
		} catch(Exception e) {
			Utilities.showErrorMsg(getShell(), e.getMessage());
		}
	}
	
	// 메뉴바 만들기
	private void setMenuBar() {
		menuBar = new Menu(getShell(), SWT.BAR);
		getShell().setMenuBar(menuBar);
		
		
		// 프로그램 정보
		{
			helpMenuItem = new MenuItem(menuBar, SWT.CASCADE);
			helpMenuItem.setText(Const.MENU_HELP);
			{
				helpMenu = new Menu(helpMenuItem);
				{
					aboutMenuItem = new MenuItem(helpMenu, SWT.CASCADE);
					aboutMenuItem.setText(Const.PROGRAM_INFO_TITLE);
					aboutMenuItem.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent evt) {
							MessageBox box = new MessageBox(getShell(), SWT.ICON_INFORMATION);
							box.setText(Const.PROGRAM_INFO_TITLE);
							box.setMessage(Const.TITLE + " " + Const.VERSION + "\n\n" + Const.PROGRAM_INFO_CONTENT);
							box.open();
						}
					});
				}
				helpMenuItem.setMenu(helpMenu);
			}
		}
	}

	// 검색버튼 눌렸을 때의 처리
	private void search() {
		
//		ProgressBarDialog progressBarDialog = new ProgressBarDialog(this.getShell());
//		progressBarDialog.open();
		
		resetDisplay();

		try {
			if (textForSearchingTextInput.getText() == null || "".equals(textForSearchingTextInput.getText())) {
				throw new Exception(Const.MESSAGE_KEYWORD_NO_INPUT);
			}
			if (directoryPathTextInput.getText() == null || "".equals(directoryPathTextInput.getText())) {
				throw new Exception(Const.MESSAGE_PATH_NO_INPUT);
			}

			SearchResultBean searchResult = new SearchResultBean();

			if (fileTypeRadio[0].getSelection()) { // .txt선택시

				Search search = new SearchText(Const.TEXT_EXTEND, directoryPathTextInput.getText(), textForSearchingTextInput.getText(), getShell());
				searchResult = search.searchPluralFiles();

				this.addTableData(searchResult);

			} else if (fileTypeRadio[1].getSelection()) { // 엑셀 파일 선택시

				Search search = new SearchExcel(Const.EXCEL_EXTEND, directoryPathTextInput.getText(), textForSearchingTextInput.getText(), getShell());
				searchResult = search.searchPluralFiles();

				this.addTableData(searchResult);
			} else if (fileTypeRadio[2].getSelection()) { // 워드 파일 선택시
				
				Search search = new SearchWord(Const.WORD_EXTEND, directoryPathTextInput.getText(), textForSearchingTextInput.getText(), getShell());
				searchResult = search.searchPluralFiles();

				this.addTableData(searchResult);
			}

			if (searchResult.getFailedFileNameList().size() > 0) {
				StringBuilder failedFileListStrBldr = new StringBuilder();
				for (String failedFileName: searchResult.getFailedFileNameList()) {
					failedFileListStrBldr.append(failedFileName + "\n");
				}
				Utilities.showInfoMsg(getShell(), Const.MESSAGE_FILE_SEARCH_FAILED + failedFileListStrBldr.toString());
			}

		} catch (Exception e) {
			Utilities.showErrorMsg(getShell(), e.getMessage());
		}
		
		searchingPath = directoryPathTextInput.getText();
	}

	// 검색결과 테이블에 추가
	private void addTableData(SearchResultBean searchResult) {

		List<String> fileNameList = searchResult.getFileNamesForSearch();
		List<SearchResultDetailBean> resultList = searchResult.getList();
		
		if (resultList != null && resultList.size() > 0) {
			String currentFileName = "";
			boolean isFirst = true;
	
			for (SearchResultDetailBean searchResultDetail : resultList) {
				
				String fileName = " ";
				if (! currentFileName.equals(searchResultDetail.getFile().getName())) {
					currentFileName = searchResultDetail.getFile().getName();
					fileName = searchResultDetail.getFile().getName();
					
					if (! isFirst) {
						TableItem tableItem = new TableItem(this.resultTable1, SWT.NONE);
						tableItem.setText(new String[]{"", "", "", "", ""});
						TableItem tableItem2 = new TableItem(this.resultTable2, SWT.NONE);
						tableItem2.setText(new String[]{"", "", "", "", ""});
					} else {
						isFirst = false;
					}
				}

				TableItem tableItem = new TableItem(this.resultTable1, SWT.NONE);
				tableItem.setText(
						new String[] {
								fileName,
								searchResultDetail.getSheetName(),
								searchResultDetail.getRowNum(),
								searchResultDetail.getColNum(),
								searchResultDetail.getContentText() != null && ! "".equals(searchResultDetail.getContentText()) ? searchResultDetail.getContentText().replace("	", "    ") : ""
						}
				);

				if (fileTypeRadio[0].getSelection() || fileTypeRadio[1].getSelection()) { // 텍스트 또는 엑셀파일 선택시
					TableItem tableItem2 = new TableItem(this.resultTable2, SWT.NONE);
					tableItem2.setText(
							new String[] {
									fileName,
									searchResultDetail.getJamakNum() != null && ! "".equals(searchResultDetail.getJamakNum()) ? searchResultDetail.getJamakNum() : "",
									searchResultDetail.getTrack() != null && ! "".equals(searchResultDetail.getTrack()) ? searchResultDetail.getTrack() : "",
									searchResultDetail.getStartTime() != null && ! "".equals(searchResultDetail.getStartTime()) ? searchResultDetail.getStartTime() : "",
									searchResultDetail.getContentText2() != null && ! "".equals(searchResultDetail.getContentText2()) ? searchResultDetail.getContentText2().replace("	", "    ") : ""
							}
				);
				}
			}

			countFileForSearch.setText(Const.COUNT_FILE_FOR_SEARCH + fileNameList.size());
			countResultFiles.setText(Const.COUNT_RESULT_FILE + searchResult.getFileNamesResultForSearch().size());
			countResult.setText(Const.COUNT_RESULT + resultList.size());
			MessageBox box = new MessageBox(getShell(), SWT.ICON_INFORMATION);
			box.setText(Const.MESSAGE_SEARCH_RESULT_TITLE);
			if (fileTypeRadio[2].getSelection()) { // 워드파일의 경우
				cTabFolder.setSelection(0);
				cTabFolder.forceFocus();
				box.setMessage(Const.MESSAGE_SEARCH_COMPLETED_MSG + Const.MESSAGE_SEARCH_COMPLETED_WORD_MSG);
			} else {
				box.setMessage(Const.MESSAGE_SEARCH_COMPLETED_MSG);
			}
			box.open();
		} else { // 검색결과가 없을 때
			countFileForSearch.setText(Const.COUNT_FILE_FOR_SEARCH + fileNameList.size());
			countResultFiles.setText(Const.COUNT_RESULT_FILE + searchResult.getFileNamesResultForSearch().size());
			countResult.setText(Const.COUNT_RESULT + 0);
			MessageBox box = new MessageBox(getShell(), SWT.ICON_INFORMATION);
			box.setText(Const.MESSAGE_SEARCH_RESULT_TITLE);
			box.setMessage(Const.MESSAGE_NO_SEARCH_RESULT_MSG);
			box.open();
		}
	}

	private void resetDisplay() {
		// 현재 테이블 표시 데이터 지우기
		this.resultTable1.removeAll();
		this.resultTable2.removeAll();
		countFileForSearch.setText(Const.COUNT_FILE_FOR_SEARCH + 0);
		countResultFiles.setText(Const.COUNT_RESULT_FILE + 0);
		countResult.setText(Const.COUNT_RESULT + 0);
	}

	public Main(Composite parent, int style) {
		super(parent, style);
	}

	
}
