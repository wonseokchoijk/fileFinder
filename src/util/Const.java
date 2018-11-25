package util;

import org.eclipse.swt.graphics.Color;

import com.cloudgarden.resource.SWTResourceManager;

public class Const {

	// 프로그램 이름
	public static final String TITLE = "ふぁいるふぁいんだー";
	public static final String VERSION = "バージョン 1.2";

	// 디폴트 선택 검색 폴더 패스
	public static final String DEFAULT_PATH = ".";
	// text파일 확장자
	public static final String[] TEXT_EXTEND = new String[]{".txt"};
	
	// 엑셀 파일 확장자
	public static final String[] EXCEL_EXTEND = new String[]{".xls", ".xlsx"};
	
	// 워드 파일 확장자
	public static final String[] WORD_EXTEND = new String[]{".doc", ".docx"};

	// 백그라운드 색 255, 171, 171
	public static Color BACKGROUND_COLOR = SWTResourceManager.getColor(252, 134, 160);

	public static final String GUIDE_TO_OPEN_FILE = "ファイルを開くにはファイル名をダブルクリックしてください。";

	// 메뉴 - 프로그램 정보
	public static final String MENU_HELP = "ヘルプ";
	public static final String PROGRAM_INFO_TITLE = "プログラム情報";
	public static final String PROGRAM_INFO_CONTENT = 	"아유코가 이 프로그램을 기뻐하며 사용하기를 바라며.\n" +
														"\n" +
														"制作: 崔元碩\n" +
														"\n" +
														"・Ver 1.0 (2014年 1月18日 〜 2014年 1月26日)\n" +
														"  - 初期バージョン開発\n" +
														"\n" +
														"・Ver 1.05 (2014年 2月3日 〜 2014年 2月5日)\n" +
														"  - 字幕番号・表示開始時間表示機能追加\n" +
														"\n" +
														"・Ver 1.10 (2014年 2月8日)\n" +
														"  - MS Wordドキュメント(doc, docx)検索機能追加\n"+
														"\n" +
														"・Ver 1.10b (2014年 3月1日)\n" +
														"  - Excelファイル検索バグ修正\n" +
														"  - 検索ファイル形式エラー時にエラーメッセージ表示追加" +
														"\n\n" +
														"・Ver 1.2 (2014年 12月7日)\n" +
														"  - 前回検索ファイルパス保存機能追加";
	
	public static final String GROUP_TEXT_FOR_SEARCHING_TITLE = "検索キーワード入力";
	public static final String GROUP_DIRECTORY_PATH_TITLE = "検索対象フォルダ指定";
	public static final String GROUP_DIRECTORY_PATH_SEARCH_BUTTON = "フォルダ選択";
	
	
	public static final String GROUP_FILE_TYPE_TITLE = "ファイル種類選択";
	public static final String GROUP_FILE_TYPE_RADIO_TEXT = "テキストファイル(txt)";
	public static final String GROUP_FILE_TYPE_RADIO_EXCEL = "Excelファイル(xls, xlsx)";
	public static final String GROUP_FILE_TYPE_RADIO_WORD = "Wordファイル(doc, docx)";

	public static final String COUNT_FILE_FOR_SEARCH = "検索ファイル数：";
	public static final String COUNT_RESULT_FILE = "キーワード存在ファイル数：";
	public static final String COUNT_RESULT = "検索結果数：";
	
	public static final String TABLE_COLUMN_NAME_FILE_NAME = "ファイル名";
	public static final String TABLE_COLUMN_NAME_SHEET_NAME = "シート名";
	public static final String TABLE_COLUMN_NAME_ROW = "行";
	public static final String TABLE_COLUMN_NAME_COL = "列";
	public static final String TABLE_COLUMN_NAME_CONTENT_TEXT = "内容";
	
	public static final String TABLE_COLUMN_NAME_JAMAK_NUM = "字幕番号";
	public static final String TABLE_COLUMN_NAME_TRACK = "トラック";
	public static final String TABLE_COLUMN_NAME_START_TIME = "表示開始時間";
	
	public static final String EXECUTE_SEARCH_BUTTON = "検索実行";
	
	public static final String TAB_ITEM_BASIC_DISPLAY = "一般表示(行番号)";
	public static final String TAB_ITEM_BUNYUK_DISPLAY = "G1エクスポートフォーマットファイル(字幕番号)";
	
	public static final String MESSAGE_FOLDER_NOT_FOUND = "存在しないフォルダです。";
	public static final String MESSAGE_FILE_NOT_FOUND = "存在しないファイルです。";
	
	public static final String MESSAGE_KEYWORD_NO_INPUT = "検索キーワードを入力してください。";
	public static final String MESSAGE_PATH_NO_INPUT = "検索対象フォルダを選択してください。";
	
	public static final String MESSAGE_ERROR_OCCURS_TITLE = "エラー発生";
	public static final String MESSAGE_ERROR_OCCURS_MSG = "エラーが発生しました。\n\n";
	public static final String MESSAGE_FOLDER_SELECT = "検索対象のフォルダを選択してください。";

	public static final String MESSAGE_INFO_TITLE = "お知らせ";
	public static final String MESSAGE_FILE_SEARCH_FAILED = "以下ファイルの形式が正しくない為、検索に失敗しました。\n手動で検索してください。\n\n";
	
	public static final String MESSAGE_SEARCH_RESULT_TITLE = "検索結果";
	public static final String MESSAGE_NO_SEARCH_RESULT_MSG = "検索結果が０件です。";
	public static final String MESSAGE_SEARCH_COMPLETED_MSG = "検索が終了しました。";
	public static final String MESSAGE_SEARCH_COMPLETED_WORD_MSG = "\n\nMS Wordドキュメントファイルの場合にはファイルの特性により\n正確な行番号とページ数の特定ができない為、該当ファイルと該当文字列内容のみを表示します。";
	
	public static final String MESSAGE_NO_EXTEND_FILE = "検索対象フォルダ内に該当拡張子のファイルが存在しません。";

	// 마지막 사용한 패스 저장용 파일 패스
	public static final String SAVE_FILE_PATH = "./conf/save.ff";
}
