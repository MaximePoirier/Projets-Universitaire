package ia;

public class IaAleatoire extends IA{

	@Override
	protected void calculTrajet() {
		int rng = (int) (Math.random()*5);
		if(rng==1){
			direction = 'h';
		} else if (rng==2){
			direction = 'b';
		} else if (rng==3){
			direction = 'd';
		} else if (rng==4){
			direction = 'g';
		} else {
			direction = ' ';
		}
		prete = true;
	}
}
