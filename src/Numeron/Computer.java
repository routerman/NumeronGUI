package Numeron;




public class Computer extends Player{
	Entry[] entry = new Entry[1000];
	//Entry buf_entry[1000];
	public int candidates;
	
	public void setEntry(Entry a){entry[a.getNumber()]=a;}
	public void deleteEntry(int a){
		entry[a].setProbablity(0);
		candidates--;
	};
	
	public void think(){
		//とりあえず、今は何も考えていない
		//候補を削除するだけで十分強い。
		//良い質問を「多くの候補を削除できる質問」と定義する。
		//iを質問したときに、各返事が来た場合にjも削除される場合はインクリメント。
		/*
		for(int i=0;i<1000;i++){
			if(entry[i].getProbablity()>0){
				entry[i].setProbablity(1);
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,0,0));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,0,1));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,0,2));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,0,3));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,1,0));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,1,1));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,1,2));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,2,0));
				entry[i].setProbablity(entry[i].getProbablity() + probability(i,2,1));
			}
		}*/
	}
	public int probability(int test,int eat,int bite){
		int count=0;
		int m;
		//deleteEntry(test);
		//xeatでなければ削除
		for(int i=0;i<entry.length;i++){
			if(entry[i].getProbablity()==0)continue;
			m=0;
			if( (test/100==i/100) )m++;
			if( (test/10%10==i/10%10) )m++;
			if( (test%10==i%10) )m++;
			if(m!=eat)count++;
		}
		//xbite以外は削除
		for(int i=0;i<entry.length;i++){
			if(entry[i].getProbablity()==0)continue;
			m=0;
			if( (i/100==test/10%10)||(i/100==test%10) )m++;
			if( (i/10%10==test/100)||(i/10%10==test%10) )m++;
			if( (i%10==test/100)||(i%10==test/10%10) )m++;
			if(m!=bite)count++;
		}
		return count;
	}

	//コールに対するジャッジから、可能性の無いものは削除。
	public void remove(int test,int eat,int bite){
		int veat,vbite;
		deleteEntry(test);
		//xeatでなければ削除
		for(int i=0;i<entry.length;i++){
			if(entry[i].getProbablity()==0)continue;
			veat=0;
			if( (test/100==i/100) )veat++;
			if( (test/10%10==i/10%10) )veat++;
			if( (test%10==i%10) )veat++;
			if(veat!=eat)deleteEntry(i);
		}
		//xbite以外は削除
		for(int i=0;i<entry.length;i++){
			if(entry[i].getProbablity()==0)continue;
			vbite=0;
			if( (i/100==test/10%10)||(i/100==test%10) )vbite++;
			if( (i/10%10==test/100)||(i/10%10==test%10) )vbite++;
			if( (i%10==test/100)||(i%10==test/10%10) )vbite++;
			if(vbite!=bite)deleteEntry(i);
		}
	}
	
	//コールに対するジャッジを基に処理をする。
	public void proc(Entry a){
		//deleteEntry(a.test);
		remove(a.getNumber(),a.getEat(),a.getBite());
	}
	//候補の数字をコンソール表示
	public void viewEntry(){
		boolean flag=false;
		System.out.println("The candidates ..");
		for(int i=0;i<1000;i++){
			if(entry[i].getProbablity()>0){
				System.out.print(entry[i].getNumber() + "(" + entry[i].getProbablity() + ")");
				flag=true;
			}else{
				//System.out.print("___(___)");
				flag=true;
			}
			if(i%10==0 && flag==true){
				System.out.println("");
				flag=false;
			}
		}
		System.out.println("The number of candidates is ");
	}
	
	//相手の数字を推測して言う（コール）
	public int call(){
		
		//select in random from candidates.
		//srand( (unsigned)time( NULL ) );
		//Random rnd = new Random();
		int count2=11;//rnd.toString().valueOf(rnd);
		do{
			for(int i=0;i<entry.length;i++){
				if(entry[i].getProbablity()>0){
					count2--;
					if(count2==0)return entry[i].getNumber();
				}
			}
		}while(count2>0);
		return 123;
	}
	
	Computer(){
		candidates=0;
		for(int i=0;i<entry.length;i++){
			entry[i] = new Entry();
			if(isValidNumber(i)){
				entry[i].setNumber(i);
				candidates++;
			}else{
				entry[i].setProbablity(0);
			}
		}
	}
}