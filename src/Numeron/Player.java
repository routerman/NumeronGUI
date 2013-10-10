package Numeron;


public class Player{
	private int answer;//答えの数字
	public int count;//質問した回数
//	list<Entry> anslist;
	//void setLastEntry(Entry test){this->anslist[count]=test;}
	public void setAnswer(int answer){this.answer=answer;}
	public int getAnswer(){return answer;}

	//public void incCount(){this.count++;}
	public int getCount(){return count;}
	public void viewAnsList(){
		/*
		for(list<Entry>::iterator it = anslist.begin(); it!=anslist.end(); it++){
			cout<<" : "<<it->number<<"="<<it->eat<<"-"<<it->bite<<endl;
		}*/
		
	}
	
	public void proc(Entry a){
		
	}
	
	//callは無い。ボタン入力。
	
	
	//judge	
	public Entry judge(int test){
		Entry a = new Entry();
		a.setNumber(test);
		if(answer/100==test/100)a.setEat(a.getEat() + 1);
		if(answer/10%10==test/10%10)a.setEat(a.getEat() + 1);
		if(answer%10==test%10)a.setEat(a.getEat() + 1);
		if((answer/100==test/10%10)||(answer/100==test%10))a.setBite(a.getBite() + 1);
		if((answer/10%10==test/100)||(answer/10%10==test%10))a.setBite(a.getBite() + 1);
		if((answer%10==test/100)||(answer%10==test/10%10))a.setBite(a.getBite() + 1);
		//cout<<test<<"is"<<a.eat<<"-"<<a.bite<<endl;
		return a;
	}

	public static boolean isValidNumber(int a){
		if( (a>=100)&&(a<1000)){
			return (a%10!=a/10%10)&&(a/10%10!=a/100)&&(a/100!=a%10);
		}
		return false;
	}

	Player(){
		count=0;
		//answer.setNumber(253);
	}
	
}
