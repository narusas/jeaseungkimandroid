package org.asky78.android.favorite;

import java.util.*;

import org.asky78.android.favorite.common.*;

import android.app.*;
import android.app.AlertDialog.Builder;
import android.content.*;
import android.os.*;
import android.text.*;
import android.util.*;
import android.view.*;
import android.view.ViewGroup.LayoutParams;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class DialogSample extends Activity {

	protected static final int SHOW_NORMAL_DIALOG = 1;
	protected static final int SHOW_PROGRESSIVE_DIALOG = 2;
	private ListView list;
	private List<String> menuText;
	private List<String> menuSummary;
	private String menuKey = "menukey";
	private String summaryKey = "summarykey";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_sample);
		
		menuText = new ArrayList<String>();
		menuText.add("기본 보기");//0
		menuText.add("HTML 적용 새창");//1
		menuText.add("프로그레시브바 새창");//2
		menuText.add("Radio 버튼 새창");//3
		menuText.add("Simple Dialog");//4
		menuText.add("Time Picker");//5
		menuText.add("Date Picker");//6
		menuText.add("다중선택 새창");//7
		menuText.add("프로그레시브바 가로 막대 진행상태");//8
		menuText.add("커스텀 프로그레시브(회전자)");//9
		menuText.add("투명 Dialog");//10
		
		menuSummary = new ArrayList<String>();
		menuSummary.add("가장 기본적인 알림상자");
		menuSummary.add("Text 에 HTML 을 적용하는 Alert 예제구현이다.");
		menuSummary.add("진행중을 나타내는 프로그레시브바 Alert 예제구현다.");
		menuSummary.add("Radio 버튼이 들어간 새창 이며 선택하면 창이 닫힌다. ");
		menuSummary.add("선택에 따른 로직구현을 위한 다이얼로그 창 구현");
		menuSummary.add("Time Picker 시간선택 컨트롤을 다이얼로그에 구현");
		menuSummary.add("Date Picker 날짜선택 컨트롤을 다이얼로그에 구현");
		menuSummary.add("다중선택을 할수 있는 Alert 예제구현이다.");
		menuSummary.add("진행상태를 수치상으로 알수 있게 가로막대 프로그레시브바로 표현한예제");
		menuSummary.add("회전자를 커스텀 이미지로 바꾼 프로그레시브바 예제");
		menuSummary.add("배경이 아무것도 없는 대화상자. 아이콘만 보이도록 세팅");//10
		
		list = (ListView) findViewById(R.id.list_dialog_samples);
		SimpleAdapter listDialogSampleAdapter = new SimpleAdapter(this//
				, getMenuDataList()//
				, android.R.layout.simple_list_item_2//
				, new String[] { menuKey, summaryKey }//
				, new int[] { android.R.id.text1, android.R.id.text2 });
		list.setAdapter(listDialogSampleAdapter);
		list.setOnItemClickListener(listItemClickListener);
	}

	/**
	 * SimpleAdapter에 사용할 리스트를 생성해 반환합니다.
	 * 
	 * @return
	 */
	private ArrayList<Map<String, String>> getMenuDataList() {
		ArrayList<Map<String, String>> arrlist = new ArrayList<Map<String, String>>();
		for (int i = 0; i < menuText.size(); i++) {
			HashMap<String, String> map = new HashMap<String, String>();
			map.put(menuKey, menuText.get(i));
			map.put(summaryKey, menuSummary.get(i));
			arrlist.add(map);
		}
		return arrlist;
	}

	/**
	 * List의 Item 클릭 이벤트 처리
	 */
	private OnItemClickListener listItemClickListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> l, View v, int position, long id) {
			switch (position) {
			case 0:
				showDialog(SHOW_NORMAL_DIALOG);// 기본보기
				break;
			case 1:
				//HTML 구현
				DialogHtmlView();
				break;
			case 2:
				// 프로그레시브바 구현
				DialogProgress();
				break;
			case 3:
				// Radio 버튼이 추가된 다중선택 창
				DialogRadio();
				break;
			case 4:
				// 가장 일반적인 Yes/NO기능구현 Dialog
				DialogSimple();
				break;
			case 5:
				// 날짜 선택 Dialog 구현
				DialogDatePicker();
				break;
			case 6:
				// 시간 선택 Dialog 구현
				DialogTimePicker();
				break;
			case 7:
				// 다중선택 옵션창
				DialogSelectOption();
				break;
			case 8:
				// 다운로드 프로그레시브 구현(가로스크롤)
				DialogProgressHorizontal();
				break;
			case 9:
				// 커스텀 프로그레시브(회전자)
				DialogCustomProgressSpinner();
				break;
			case 10:
				// 투명한 대화상자(이미지만 보임)
				DialogTransparentBackground();
				break;
			default:
				break;
			}
		}
	};
	
	private void DialogTransparentBackground(){
		LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.dialog_transparent, null);
        Dialog dialog = new Dialog(this, R.style.TransparentDialog); //!! 투명한 스타일을 적용한다.
        dialog.setCancelable(true);
        dialog.addContentView(customView, new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));//생성한 Dialog에 view를 추가.
        dialog.show();
	}
	
	private void DialogCustomProgressSpinner(){
		try {
			Builder builder = new AlertDialog.Builder(this);
			builder.setCancelable(true);
			Dialog dialog = builder.create();
			dialog.requestWindowFeature(Window.FEATURE_PROGRESS);
			dialog.setContentView(R.layout.layout_custom_progress_spinner);
			dialog.show();
		} catch (Exception e) {
			Log.i(Constants.TAG, e.getMessage());
		}
	}
	
	private void DialogSelectOption() {
		final String items[] = { "item1", "item2", "item3" };
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle("Title");
		ab.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// 각 리스트를 선택했을때
			}
		}).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// OK 버튼 클릭시 , 여기서 선택한 값을 메인 Activity 로 넘기면 된다.
			}
		}).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Cancel 버튼 클릭시
			}
		});
		ab.show();
	}

	private void DialogHtmlView() {
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setMessage(Html.fromHtml("<strong><font color=\"#ff0000\"> " + "Html 표현여부 " + "</font></strong><br>HTML 이 제대로 표현되는지 본다."));
		ab.setPositiveButton("ok", null);
		ab.show();
	}

	private void DialogProgress() {
		try {
			ProgressDialog.show(DialogSample.this, "Loding..", "잠시만 기다려 주세요 ...", true, true);
		} catch (Exception e) {
			Log.i(Constants.TAG, e.getMessage());
		}
	}

	private void DialogRadio() {
		final CharSequence[] PhoneModels = { "iPhone", "Nokia", "Android" };
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
		alt_bld.setIcon(R.drawable.icon);
		alt_bld.setTitle("Select a Phone Model");
		alt_bld.setSingleChoiceItems(PhoneModels, -1, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int item) {
				Toast.makeText(getApplicationContext(), "Phone Model = " + PhoneModels[item], Toast.LENGTH_SHORT).show();
				dialog.cancel();
			}
		});
		AlertDialog alert = alt_bld.create();
		alert.show();
	}

	private void DialogSimple() {
		AlertDialog.Builder alt_bld = new AlertDialog.Builder(this);
		alt_bld.setMessage("Do you want to close this window ?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Action for 'Yes' Button
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				// Action for 'NO' Button
				dialog.cancel();
			}
		});
		AlertDialog alert = alt_bld.create();
		// Title for AlertDialog
		alert.setTitle("Title");
		// Icon for AlertDialog
		alert.setIcon(R.drawable.icon);
		alert.show();
	}

	private void DialogTimePicker() {
		TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
			public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
				Toast.makeText(DialogSample.this, "Time is=" + hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
			}
		};
		TimePickerDialog alert = new TimePickerDialog(this, mTimeSetListener, 0, 0, false);
		alert.show();
	}

	private void DialogDatePicker() {
		Calendar c = Calendar.getInstance();
		int cyear = c.get(Calendar.YEAR);
		int cmonth = c.get(Calendar.MONTH);
		int cday = c.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
			// onDateSet method
			public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
				String date_selected = String.valueOf(monthOfYear + 1) + " /" + String.valueOf(dayOfMonth) + " /" + String.valueOf(year);
				Toast.makeText(DialogSample.this, "Selected Date is =" + date_selected, Toast.LENGTH_SHORT).show();
			}
		};
		DatePickerDialog alert = new DatePickerDialog(this, mDateSetListener, cyear, cmonth, cday);
		alert.show();
	}
	ProgressDialog progressDialogByHorizontalDownloadBar;
	private void DialogProgressHorizontal(){
		progressDialogByHorizontalDownloadBar = new ProgressDialog(this);
		progressDialogByHorizontalDownloadBar.setCancelable(true);
		progressDialogByHorizontalDownloadBar.setTitle("title line");
		progressDialogByHorizontalDownloadBar.setMessage("message line");
		//%값은 setProgress(int)/max(int) 값을 자동으로 계산해서 들어간다.
		//%값이 100%를 넘으면 백그라운드 진행은 계속되나 값은 100%로 고정된다. 
		progressDialogByHorizontalDownloadBar.setMax(100); 
		progressDialogByHorizontalDownloadBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(DialogInterface dialog) {
				Log.i(Constants.TAG, "취소됬네요~");
			}
		});
		progressDialogByHorizontalDownloadBar.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				Log.i(Constants.TAG, "종료됬네요~");
			}
		});
		//STYLE_SPINNER 인경우 icon 과 title이 첫행에 회전하는 이미지와 메세지가 2행에 표시된다. setProgress(int)및 %는 표시되지 않는다.
		//STYLE_HORIZONTAL 인경우 icon 과 title이 첫행에 메세지가 2행에 표시된다.
		//	3행에 가로방향으로 진행하는 표시바가 나오고 그 아래로 숫자가 int% setProgress(int)/setMax(int) 순으로 표시된다.
		progressDialogByHorizontalDownloadBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		progressDialogByHorizontalDownloadBar.setIcon(R.drawable.icon);
		progressDialogByHorizontalDownloadBar.show();
		new Thread(new Runnable() {
			public void run() {
				try{
					int count = 0;
					while(count <= 100){
						progressDialogByHorizontalDownloadBar.setProgress(count);
						SystemClock.sleep(200);
						count += 10;
					}
					progressDialogByHorizontalDownloadBar.dismiss();
				}catch (Exception e) {
					Log.i(Constants.TAG, e.getMessage());
				}
			}
		}).start();
	}
	
	/**
	 * Dialog 생성(최초 한번만 실행됩니다.) activity에서 자원을 관리합니다. 내용을 변경하려면 onPrepareDialog를 사용합니다. activity에서 관리하는 자원을 해제 하려면
	 * removeDialog(id)를 사용합니다. 별도로 생성한 Dialog를 activity에서 관리 하도록 하려면 dialog.setOwnerActivity(activity)를 사용합니다. 이 예제에선
	 * SHOW_NORMAL_DIALOG 하나만 createDialog사용 그외에는 일반 생성함.
	 */
	protected Dialog onCreateDialog(int id) {
		super.onCreateDialog(id);
		Dialog dialog = null;
		switch (id) {
		case SHOW_NORMAL_DIALOG:
			dialog = new AlertDialog.Builder(this)//
					.setIcon(R.drawable.infomation)//
					.setTitle("TITLE")//
					.setMessage("MESSAGE")// setItems 류 메서드 사용시 사용할수 없다.
					.setPositiveButton("Positive", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.dismiss();
						}
					})//
					.create();//
			break;
		}
		return dialog;
	}

	@Override
	protected void onPrepareDialog(int id, Dialog dialog) {
		super.onPrepareDialog(id, dialog);
	}
}
