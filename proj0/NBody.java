/******************************************************************************
 *  Compilation:  javac NBody.java 
 *  Execution:    java NBody 157788000.0 25000.0 data/planets.txt
 *  Dependencies: In.java StdDraw.java
 ******************************************************************************/

public class NBody {
	public static double readRadius(String file){
		if (file == null){
			System.out.println("Please supply a file as a command line argument.");
			/* System.exit ends the program early. */
			System.exit(0);
		}

		/* Start reading in file */
		In in = new In(file);
		
		int N = in.readInt();
		double R = in.readDouble();

		return R;
	}

	public static Planet[] readPlanets(String file){
		In in = new In(file);
		int N = in.readInt();
		double R = in.readDouble();
		Planet[] planets = new Planet[N];

		for(int i = 0; i < N; i++){
			planets[i] = new Planet(0, 0, 0, 0, 0, "");
			planets[i].xxPos = in.readDouble();
			planets[i].yyPos = in.readDouble();
			planets[i].xxVel = in.readDouble();
			planets[i].yyVel = in.readDouble();
			planets[i].mass = in.readDouble();
			planets[i].imgFileName = in.readString();
		}

		

		return planets;
	}

	public static void main(String[] args){
		if (args.length < 3){
			System.out.println("Need 3 arguments: T, dt, filename");
			/* System.exit ends the program early. */
			System.exit(0);
		}
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		double radius = NBody.readRadius(filename);
		Planet[] planet = NBody.readPlanets(filename);
		int N = planet.length;

		/* Draw background */
		String imageToDraw = "images/starfield.jpg";
		StdDraw.setScale(-radius, radius);

		/* Clears the drawing window. */
		StdDraw.clear();

		/* Stamps the image */
		StdDraw.picture(0, 0, imageToDraw);

		/* Shows the drawing to the screen */	
		for (Planet p : planet){
			p.draw();
		}
		StdDraw.show();

		/* Creating an Animation */

		double time = 0.0;
		
		double[] xForces = new double[N];
		double[] yForces = new double[N];

		while(time < T){
			for (int i = 0; i < N; i++){
				xForces[i] = planet[i].calcNetForceExertedByX(planet);
				yForces[i] = planet[i].calcNetForceExertedByY(planet);
			}
			for (int j = 0; j < N; j++){
				planet[j].update(dt, xForces[j], yForces[j]);
			}

			StdDraw.setScale(-radius, radius);

			/* Clears the drawing window. */
			StdDraw.clear();

			/* Stamps the image */
			StdDraw.picture(0, 0, imageToDraw);

			/* Shows the drawing to the screen */	
			for (Planet p : planet){
				p.draw();
			}
			/* Shows the drawing to the screen, and waits 10 milliseconds. */	
			StdDraw.show(10);

			time += dt;
		}


	}
}
