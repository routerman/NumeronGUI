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
	//interface
	JPanel contentPane = new JPanel();//ボタンのインターフェース
	BorderLayout borderLayout1 = new BorderLayout();//レイアウト
	JLabel candidate = new JLabel("コンピュータの残りの候補数:"); 
	JTextField input = new JTextField(""); //選んだ数字を表示	
	Entry sample;
	//Game
	boolean gameover=false;
	boolean start=false;//最初は数字を決める必要がある。
	boolean afterEnter = false;
	Player player;
	Computer computer;
	
	//フレームのビルド
	public static void main( String[] args ){
		new Numeron();
	}

	public Numeron() {
		contentPane.setLayout(borderLayout1);
		this.setSize(new Dimension(500, 600));
		this.setTitle("Numeron");
		this.setContentPane(contentPane);

		contentPane.add(candidate, BorderLayout.NORTH); //候補をラベル表示
		
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
	}

	/* 有効な数字かどうか */
	public boolean isValidNumber(int a){
		if( (a>=100)&&(a<1000)){
			return ( a%10!=a/10%10 )&&( a/10%10!=a/100 )&&( a/100!=a%10 );
		}
		return false;
	}
	//入力された数字を基に処理をする。人間とコンピュータの共通処理部
	public void Proc(int input_number){
		if(!start){
			//人間の答えを設定する
			if( isValidNumber(input_number) ){
				//人間の数字を決める。
				player.setAnswer(input_number);
				//乱数でコンピュータの数字を決める
				computer.setAnswer(0);
				start=true;
				System.out.println("START!");
			}else{
				//無効な数字だともう一度
				System.out.println("ERROR:Invalid number!");
			}
		}else{
			//プレーヤーのターン(とりあえず人間が先攻)
			if( isValidNumber(input_number) ){
				//有効な数字ならば、Playerの処理を開始する。
				Entry answer = computer.judge(input_number);
				if(answer.getEat()==3){
					//3-0なら終了する
					System.out.println("PLAYER WIN!");
					gameover=true;
				}else{
					player.proc(answer);
				}
			}else{
				System.out.println("ERROR:Invalid number!");
				return;
			}
			//Computerのターン。
			//Computerの候補数を表示する.
			computer.viewEntry();
			input_number = computer.call();
			if( isValidNumber(input_number) ){
				Entry answer = player.judge(input_number);
				if(answer.getEat()==3){
					//3-0なら終了する
					System.out.println("COMPUTER WIN!");
					gameover=true;
				}else{
					computer.proc(answer);
				}
			}else{
				System.out.println("ERROR:Invalid number!");
				return;
			}
				
		}
	}
	
	/* 数字を入力するボタンの定義 */
	public class NumberButton extends JButton implements ActionListener {
		public NumberButton(String keyTop) {
			super(keyTop); //JButtonクラスのコンストラクタを呼び出す
			this.addActionListener(this); //このボタンにアクションイベントのリスナを設定
		}
		public void actionPerformed(ActionEvent evt) {
			if(afterEnter){
				input.setText("");
				afterEnter = false;
			}
			String keyNumber = this.getText(); //ボタンの名前を取り出す
			input.setText(input.getText() + keyNumber);
		}
	}
	/* Enterボタンを定義 */
	public class EnterButton extends JButton implements ActionListener {
		public EnterButton(String op) {
			super(op);
			this.addActionListener(this);
		}
		public void actionPerformed(ActionEvent e) {
			if(!gameover){
				afterEnter=true;
				Proc( Integer.parseInt( input.getText() ) );
				if(start)setText("コール");
			}
		}
	}

	/* クリアボタンの定義 */
	public class ClearButton extends JButton implements ActionListener {
		public ClearButton(String op) {
			super(op);
			this.addActionListener(this);
		}
		public void actionPerformed(ActionEvent evt) {
			input.setText("");
		}
	}
}