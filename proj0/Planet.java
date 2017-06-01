/******************************************************************************
 *  Compilation:  javac Planet.java 
 *  Execution:    java Planet
 *  Dependencies: In.java StdDraw.java
 ******************************************************************************/

public class Planet {
	double xxPos;
	double yyPos;
	double xxVel;
	double yyVel;
	double mass;
	String imgFileName;

	public static final double G = 6.67E-11;

	public Planet(){
		xxPos = 0;
		yyPos = 0;
		xxVel = 0;
		yyVel = 0;
		mass = 0;
		imgFileName = "";
	}

	public Planet(double xP, double yP, double xV, 
		double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		return Math.sqrt(((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + 
			(this.yyPos - p.yyPos) * (this.yyPos - p.yyPos)));
	}

	public double calcForceExertedBy(Planet p){
		return (G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p)));
	}

	public double calcNetForceExertedByX(Planet[] allPlanets){
		double Fx = 0;
		int i = 0;
		while(i < allPlanets.length){
			if (this.calcDistance(allPlanets[i]) == 0){
				i++;
				continue;
			}
			Fx += this.calcForceExertedBy(allPlanets[i]) * (allPlanets[i].xxPos - this.xxPos) / this.calcDistance(allPlanets[i]);
			i++;
		}
		return Fx;
	}

	public double calcNetForceExertedByY(Planet[] allPlanets){
		double Fy = 0;
		int i = 0;
		while(i < allPlanets.length){
			if (this.calcDistance(allPlanets[i]) == 0){
				i++;
				continue;
			}
			Fy += this.calcForceExertedBy(allPlanets[i]) * (allPlanets[i].yyPos - this.yyPos) / this.calcDistance(allPlanets[i]);
			i++;
		}
		return Fy;
	}

	public void update(double t, double fx, double fy){
		double ax = fx / this.mass;
		double ay = fy / this.mass;
		xxVel += ax * t;
		yyVel += ay * t;
		xxPos += xxVel * t;
		yyPos += yyVel * t;
	}	

	public void draw(){
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
	}	
}


