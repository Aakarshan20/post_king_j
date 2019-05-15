package view;
import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.json.JSONObject;

import util.tools;
import util.RoundButton;
import util.HttpConnector;

/*
 * 2018-05-08
 * 更改左上角日期時間
 * 2018-05-09
 * textarea 亂碼問題
 */
public class Index extends JFrame implements ActionListener{
	draw d ;
	Image titleIcon;
	JTextField url, key1, key2, key3, key4, key5, key6, key7, value1, value2, value3, value4, value5, value6, value7;
	JButton send, clear, init ;
	ImageIcon sendIcon, doubleRingIcon;
	JTextArea result ;
	JScrollPane jsp, jsp_outside;
	
	SimpleDateFormat sdFormat, timeFormat, weekDayFormat, dateFormat;
	Date current;
	JLabel timeNow, weekDayNow, dateNow, doubleRingLabel;
	
	//javax.swing.Time 可以定時觸發Action事件
	javax.swing.Timer timer;
	
	String 	sendTime, now, url_content, 
			key1_content, key2_content, key3_content, key4_content, key5_content, key6_content, key7_content,
			value1_content, value2_content, value3_content, value4_content, value5_content, value6_content, value7_content;
	
	String[] keys,values;
	
	
	
	public Index() {
		Container ct = this.getContentPane();
		
		titleIcon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/icon2.png"));//讀取視窗的小圖標
		
		
		timer = new Timer(1000, this);//每隔1秒觸發ActionEvent事件
		timer.start();//啟動定時器
		
		//左上小時鐘
		sdFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		timeFormat = new SimpleDateFormat("HH:mm:ss");
		current = new Date();
		timeNow = new JLabel(timeFormat.format(current));
		timeNow.setBounds(75, 7, 150, 49);
		timeNow.setFont(tools.ab25);
		timeNow.setBackground(Color.black);
		timeNow.setForeground(Color.white);
		
		//左上星期
		weekDayFormat = new SimpleDateFormat("EEEE");
		weekDayNow = new JLabel(weekDayFormat.format(current));
		weekDayNow.setBounds(115, 30, 500, 49);
		weekDayNow.setFont(tools.f14);
		weekDayNow.setBackground(Color.black);
		weekDayNow.setForeground(Color.white);
		
		//左上日期
		dateFormat = new SimpleDateFormat("MMM dd, yyyy");
		dateNow = new JLabel(dateFormat.format(current));
		dateNow.setBounds(100, 50, 500, 49);
		dateNow.setFont(tools.f12);
		dateNow.setBackground(Color.black);
		dateNow.setForeground(Color.white);
		//timeNow.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		
		//for url request
		url = new JTextField();
		url.setBounds(240, 10, 500, 36);
		url.setFont(tools.f16);
		url.setBackground(Color.black);
		url.setForeground(Color.white);
		url.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//key1
		key1 = new JTextField();
		key1.setBounds(16, 170, 100, 26);
		key1.setFont(tools.f16);
		key1.setBackground(Color.black);
		//key1.setForeground(new Color(44,103,143));
		key1.setForeground(Color.white);
		key1.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//value1
		value1 = new JTextField();
		value1.setBounds(198, 170, 168, 26);
		value1.setFont(tools.f16);
		value1.setBackground(Color.black);
		//value1.setForeground(new Color(44,103,143));
		value1.setForeground(Color.white);
		value1.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//key2
		key2 = new JTextField();
		key2.setBounds(48, 215, 100, 26);
		key2.setFont(tools.f16);
		key2.setBackground(Color.black);
		//key2.setForeground(new Color(44,103,143));
		key2.setForeground(Color.white);
		key2.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//value2
		value2 = new JTextField();
		value2.setBounds(242, 215, 168, 26);
		value2.setFont(tools.f16);
		value2.setBackground(Color.black);
		//value2.setForeground(new Color(44,103,143));
		value2.setForeground(Color.white);
		value2.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//key3
		key3 = new JTextField();
		key3.setBounds(92, 258, 100, 26);
		key3.setFont(tools.f16);
		key3.setBackground(Color.black);
		//key3.setForeground(new Color(44,103,143));
		key3.setForeground(Color.white);
		key3.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//value3
		value3 = new JTextField();
		value3.setBounds(285, 258, 168, 26);
		value3.setFont(tools.f16);
		value3.setBackground(Color.black);
		//value3.setForeground(new Color(44,103,143));
		value3.setForeground(Color.white);
		value3.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//doubleRIng
		doubleRingIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/doubleRing.gif")));
		doubleRingLabel = new JLabel();
		doubleRingLabel.setIcon(doubleRingIcon);
		doubleRingLabel.setBounds(990, 140, 200, 200);
		
		
		//key4
		key4 = new JTextField();
		key4.setBounds(1094,345, 100, 26);
		key4.setFont(tools.f16);
		key4.setBackground(Color.black);
		//key4.setForeground(new Color(44,103,143));
		key4.setForeground(Color.white);
		key4.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//value4
		value4 = new JTextField();
		value4.setBounds(835, 346, 168, 26);
		value4.setFont(tools.f16);
		value4.setBackground(Color.black);
		//value4.setForeground(new Color(44,103,143));
		value4.setForeground(Color.white);
		value4.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//key5
		key5 = new JTextField();
		key5.setBounds(1064, 388, 100, 26);
		key5.setFont(tools.f16);
		key5.setBackground(Color.black);
		//key5.setForeground(new Color(44,103,143));
		key5.setForeground(Color.white);
		key5.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//value5
		value5 = new JTextField();
		value5.setBounds(806, 390, 168, 26);
		value5.setFont(tools.f16);
		value5.setBackground(Color.black);
		//value5.setForeground(new Color(44,103,143));
		value5.setForeground(Color.white);
		value5.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		
		//key6
		key6 = new JTextField();
		key6.setBounds(1027, 430, 100, 26);
		key6.setFont(tools.f16);
		key6.setBackground(Color.black);
		//key6.setForeground(new Color(44,103,143));
		key6.setForeground(Color.white);
		key6.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		
		//value6
		value6 = new JTextField();
		value6.setBounds(768, 433, 168, 26);
		value6.setFont(tools.f16);
		value6.setBackground(Color.black);
		//value6.setForeground(new Color(44,103,143));
		value6.setForeground(Color.white);
		value6.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//key7
		key7 = new JTextField();
		key7.setBounds(988, 475, 100, 26);
		key7.setFont(tools.f16);
		key7.setBackground(Color.black);
		//key7.setForeground(new Color(44,103,143));
		key7.setForeground(Color.white);
		key7.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		//value7
		value7 = new JTextField();
		value7.setBounds(708, 478, 168, 26);
		value7.setFont(tools.f16);
		value7.setBackground(Color.black);
		//value7.setForeground(new Color(44,103,143));
		value7.setForeground(Color.white);
		value7.setBorder(BorderFactory.createLineBorder(new Color(44,103,143)));
		
		this.setIconImage(titleIcon);//設置左上圖標
		
		//clear
		clear = new JButton("clear");
		clear.setBackground(new Color(2,79,73));
		clear.setForeground(Color.white);
		clear.setFont(tools.f16);
		clear.setBounds(1050, 660, 100,35);
		clear.addActionListener(this);
		clear.setActionCommand("clear");
		
		//輸出結果區塊 reault
		result = new JTextArea(1092, 270);
		result.setBackground(new Color(44,103,143));
		result.setForeground(Color.white);
		result.setFont(tools.f16);
		
		jsp = new JScrollPane(result);//加入卷軸
		jsp.setBounds(50,700, 1092,270);
		
		
		/*
		send = new JButton();
		sendIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/send.png")));
		send.setBounds(527,265,140,140);//指定位置
		send.setIcon(sendIcon);
		send.addActionListener(this);
		send.setActionCommand("send");*/
		
		d = new draw();//背景
		d.setBounds(0, 0, 1195, 1070);
		d.setOpaque(false);// 設置透明度
		
		
		send = new RoundButton("這是一個圓型按鈕"); // 初始化按钮一
		Dimension dim = send.getPreferredSize(); // 得到按钮一的最佳尺寸
		
		dim.setSize(140, 140); // 更改长宽为长宽中的最大值
		send.setPreferredSize(dim); // 设置最佳尺寸
		
		send.setBackground(Color.white); // 设置按钮的背景颜色
		
		
		send.setBounds(527,265,140,135);
		sendIcon = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/send_round.png")));
		send.setIcon(sendIcon);
		send.setBorderPainted(false);//去除按鈕邊框
		send.addActionListener(this);
		send.setActionCommand("send");
		
		ct.add(timeNow);
		ct.add(weekDayNow);
		ct.add(dateNow);
		ct.add(url);
		ct.add(key1);
		ct.add(key2);
		ct.add(key3);
		ct.add(key4);
		ct.add(key5);
		ct.add(key6);
		ct.add(key7);
		
		ct.add(value1);
		ct.add(value2);
		ct.add(value3);
		ct.add(value4);
		ct.add(value5);
		ct.add(value6);
		ct.add(value7);
		
		ct.add(doubleRingLabel);
		ct.add(clear);
		ct.add(send);
		
		//ct.add(result);
		ct.add(jsp);
		ct.add(d);//加入背景圖
		
		
		this.setLayout(null);
		this.setSize(1210, 1070);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]) {
		Index index = new Index();
		System.out.println("sending...");
	}

	
	public void actionPerformed(ActionEvent arg0) {//監聽函數
		String command = arg0.getActionCommand();//獲得指令
		current = new Date();//時間obj
		this.timeNow.setText(timeFormat.format(current));//使用H:i:s來格式化時間
		if(command != null) {//如果指令不為空(為空時 代表只要更新時間)
			switch(command) {//判斷指令
			case "send":
				current = new Date();//時間obj
				sendTime = sdFormat.format(current);//使用 YYYY-MM-dd HH:mm:ss 格式化時間
				result.append("=================================START   at  " +sendTime + " =================================\r\n");
				
				//取得各欄資料
				url_content = url.getText();
				key1_content = key1.getText();
				key2_content = key2.getText();
				key3_content = key3.getText();
				key4_content = key4.getText();
				key5_content = key5.getText();
				key6_content = key6.getText();
				key7_content = key7.getText();
				
				value1_content = value1.getText();
				value2_content = value2.getText();
				value3_content = value3.getText();
				value4_content = value4.getText();
				value5_content = value5.getText();
				value6_content = value6.getText();
				value7_content = value7.getText();
				
				int parameterLength = 0;//取得想傳出的參數數量
				
				ArrayList <String> keys_list = new ArrayList();//建立兩個ArrayList接收
				ArrayList <String> values_list = new ArrayList();
				
				if(!url_content.equals("")) {//有網址才做下面的事
					if(!key1_content.equals("") && !value1_content.equals("")) {
						keys_list.add(key1_content);
						values_list.add(value1_content);
						parameterLength++;
					}
					if(!key2_content.equals("") && !value2_content.equals("")) {
						keys_list.add(key2_content);
						values_list.add(value2_content);
						parameterLength++;
					}
					if(!key3_content.equals("") && !value3_content.equals("")) {
						keys_list.add(key3_content);
						values_list.add(value3_content);
						parameterLength++;
					}
					if(!key4_content.equals("") && !value4_content.equals("")) {
						keys_list.add(key4_content);
						values_list.add(value4_content);
						parameterLength++;
					}
					if(!key5_content.equals("") && !value5_content.equals("")) {
						keys_list.add(key5_content);
						values_list.add(value5_content);
						parameterLength++;
					}
					if(!key6_content.equals("") && !value6_content.equals("")) {
						keys_list.add(key6_content);
						values_list.add(value6_content);
						parameterLength++;
					}
					if(!key7_content.equals("") && !value7_content.equals("")) {
						keys_list.add(key7_content);
						values_list.add(value7_content);
						parameterLength++;
					}
					
				}
				
				if(parameterLength >0) {//只要上面其中一組有值
					keys = new String[parameterLength];
					values = new String[parameterLength];
					
					for(int i=0; i<keys_list.size(); i++) {//轉成String[] 待送入 HttpConnector
						keys[i] = keys_list.get(i);
						values[i] = values_list.get(i);
					}
					
				
					
					HttpConnector hc = new HttpConnector();//此class在util package內
					
					try {
						String[] response = hc.sendPost(url_content, keys, values);
						result.append("Response Code : " + response[0]);
						result.append("\r\n");
						result.append(response[1]);
						result.append("\r\n");
					} catch (java.io.FileNotFoundException fne) {
						result.append("錯誤: 找不到該路由");
						result.append("\r\n");
						
						
					} catch (java.net.MalformedURLException mfue) {
						System.out.println("no protofal");
						result.append("錯誤: 網址有誤");
						result.append("\r\n");

					} catch (Exception e) {
						
						e.printStackTrace();
					}finally {
						
					}
				
				}
				
				//測試用 post
				/*
				value1_content = value1.getText();
				
				
				HttpConnector hc = new HttpConnector();//此class在util package內
				try {
					//url_content = "http://test.mg.plus20.marq.com.tw/api/event/update";
					url_content = "http://test.mg.plus20.marq.com.tw/api/event/fetch_init_data";
					String[] keys = {"session_id", "session_account", "sig", "event_id", "description"};
					String[] values = {"1e0d2a5a88ae00184bae156d467573a7","admin","0259019521031387","428", 
							//		value1_content};
							"從B2到13樓極速挑戰賽2"};
					//String[] values2 = {"1e0d2a5a88ae00184bae156d467573a7","admin","0259019521031387","428", 
					//"中文"};
					String[] response = hc.sendPost(url_content, keys, values);
					
					result.append("Response Code : " + response[0]);
					result.append("\r\n");
					
					String testString = response[1].substring(85, 91);
					result.append(testString);
					value7.setText(testString);
					result.append("\r\n");
					result.append(response[1]);
					
					result.append("\r\n");
				} catch (Exception e) {

					e.printStackTrace();
				}*/
				/* 
				 
				HttpConnector hc = new HttpConnector();//此class在util package內
				try {
					//url_content = "http://test.mg.plus20.marq.com.tw/api/event/update";
					url_content = "http://test.mg.plus20.marq.com.tw/api/event/fetch_init_data";
					String[] keys = {"session_id", "session_account", "sig", "event_id", "description"};
					String[] values = {"1e0d2a5a88ae00184bae156d467573a7","admin","0259019521031387","428", 
									"從B2到13樓極速挑戰賽"};
					//String[] values2 = {"1e0d2a5a88ae00184bae156d467573a7","admin","0259019521031387","428", 
					//"中文"};
					String[] response = hc.sendPost(url_content, keys, values);
					//String[] response = hc.sendPost(url_content, keys, values2);
					result.append("Response Code : " + response[0]);
					result.append("\r\n");
					result.append(response[1]);
					result.append("\r\n");
				} catch (Exception e) {

					e.printStackTrace();
				}
				*/
				break;
				
			case "clear":
				result.selectAll();
				result.replaceSelection("");
				break;
			}
		}
		
	}
	
	class draw extends JPanel{//JPanel作用: 畫背景
		
		int width = 0;
		int height = 0;
		
		public draw(int width, int height) {
			this.width = width;
			this.height = height;
		}
		public draw() {
			
		}
		public void paintComponent(Graphics g) {
			Image image;
			//image=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/background.png"));
			image=Toolkit.getDefaultToolkit().getImage(getClass().getResource("/image/background4.png"));
			g.drawImage(image, 0, 0,1195,1070, this);
			
		}
	}
}
