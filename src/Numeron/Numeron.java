package Numeron;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Numeron extends JFrame {
	private static final long serialVersionUID = 1L;
	//interface
	JPanel contentPane = new JPanel();//ボタンのインターフェース
	BorderLayout borderLayout1 = new BorderLayout();//レイアウト
	JLabel kouho = new JLabel("コンピュータの残りの候補数:"); 
	JTextField input = new JTextField(""); //選んだ数字を表示	
	//Numeron number
	Entry sample;
	//boolean isStacked = false; //stackedValueに数値を入力したかどうか
	boolean turn;
	boolean start;
	
	Player player;
	Computer computer;;
	//フレームのビルド
	public static void main( String[] args ){
		new Numeron();
	}

	public Numeron() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(500, 600));
		this.setTitle("Numeron");
		this.setContentPane(contentPane);

		contentPane.add(kouho, BorderLayout.NORTH); //候補をラベル表示

		
		contentPane.add(input, BorderLayout.CENTER); //テキストフィールドを配置
		
		JPanel keyPanel = new JPanel(); //ボタンを配置するパネルを用意
		keyPanel.setLayout(new GridLayout(4, 3)); //4行4列のGridLayoutにする
		contentPane.add(keyPanel, BorderLayout.SOUTH);

		keyPanel.add(new NumberButton("7"), 0); //ボタンをレイアウトにはめこんでいく
		keyPanel.add(new NumberButton("8"), 1);
		keyPanel.add(new NumberButton("9"), 2);
		keyPanel.add(new NumberButton("4"), 3);
		keyPanel.add(new NumberButton("5"), 4);
		keyPanel.add(new NumberButton("6"), 5);
		keyPanel.add(new NumberButton("1"), 6);
		keyPanel.add(new NumberButton("2"), 7);
		keyPanel.add(new NumberButton("3"), 8);
		keyPanel.add(new NumberButton("0"), 9);
		keyPanel.add(new ClearButton("取り消す"), 10);
		keyPanel.add(new EnterButton("始める"), 11);

		//contentPane.add(new ClearButton(), BorderLayout.SOUTH);//Cボタンを配置する
		this.setVisible(true);
		player= new Player();
		computer = new Computer();
		start=false;//最初は数字を決める必要がある。
	}

	/* テキストフィールドに引数の文字列をつなげる */
	public void appendInput(String c) {
		input.setText(input.getText() + c); //押したボタンの名前をつなげる
	}
	
	/* 有効な数字かどうか */
	public boolean isValidNumber(int a){
		if( (a>=100)&&(a<1000)){
			return (a%10!=a/10%10)&&(a/10%10!=a/100)&&(a/100!=a%10);
		}
		return false;
	}

	public void Proc(int input_number){
		if(!start){
			if( isValidNumber(input_number) ){
				//人間の数字を決める。
				player.setAnswer(input_number);
				//乱数でコンピュータの数字を決める
				computer.setAnswer(0);
				//this.setText("コール");
				start=true;
			}else{
				//無効な数字だともう一度
				System.out.println("ERROR:Invalid number!");
			}
		}else{
			//プレーヤーのターン
			if( isValidNumber(input_number) ){
				//有効な数字ならば、Playerの処理を開始する。
				Entry answer = computer.judge(input_number);
				if(answer.getEat()==3){
					//3-0なら終了する
					
				}else{
					player.proc(answer);
					
				}
			}else{
				System.out.println("ERROR:Invalid number!");
				return;
			}
			//Computerのターン。
			input_number=computer.call();
			if( isValidNumber(input_number) ){
				Entry answer = player.judge(input_number);
				if(answer.getEat()==3){
					//3-0なら終了する
					
				}else{
					computer.proc(answer);
				}
			}else{
				System.out.println("ERROR:Invalid number!");
				return;
			}
				
			//Computerの候補数を表示する.
		}
	}

	
	/* 数字を入力するボタンの定義 */
	public class NumberButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;

		public NumberButton(String keyTop) {
			super(keyTop); //JButtonクラスのコンストラクタを呼び出す
			this.addActionListener(this); //このボタンにアクションイベントのリスナを設定
		}

		public void actionPerformed(ActionEvent evt) {
			String keyNumber = this.getText(); //ボタンの名前を取り出す
			appendInput(keyNumber); //ボタンの名前をテキストフィールドにつなげる
		}
	}
	/* Enterボタンを定義 */
	public class EnterButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;
		public EnterButton(String op) {
			super(op);
			this.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			int input_number = Integer.parseInt( input.getText() );
			Proc(input_number);
		}
	}

	/* クリアボタンの定義 */
	public class ClearButton extends JButton implements ActionListener {
		private static final long serialVersionUID = 1L;
		public ClearButton(String op) {
			super(op);
			this.addActionListener(this);
		}
		public void actionPerformed(ActionEvent evt) {
			input.setText("");
		}
	}
}