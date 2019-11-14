import java.io.FileWriter;
import java.io.File;

class HW4 {
	static String outfile;
	static FileWriter myfile;

	static void moveTower(int disk, char source, char dest, char spare) {
		try{
			if (disk == 1) {
				myfile.write(disk + " " + source + " " + dest + "\n");
			} else {
				moveTower(disk - 1, source, spare, dest);
				myfile.write(disk + " " + source + " " + dest + "\n");
				moveTower(disk - 1, spare, dest, source);
			}	
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
		
	}

	static void moveTower(int disk, char source, char dest, char spare1, char spare2) {
		try{
			if (disk == 0) {
				return;
			}
			else if (disk == 1) { 
				myfile.write(disk + " " + source + " " + dest + "\n");
			} else {
				moveTower(disk - 2, source, spare1, spare2, dest);
				myfile.write((disk - 1) + " " + source + " " + spare2 + "\n");
				myfile.write(disk + " " + source + " " + dest + "\n");
				myfile.write((disk - 1) + " " + spare2 + " " + dest + "\n");
				moveTower(disk - 2, spare1, dest, source, spare2);
			}	
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
			
	}

	static void moveTower(int disk, char source, char dest, char spare1, char spare2, char spare3) {
		try{
			if (disk <= 0) {
				return;
			}
			else if (disk == 1) {
				myfile.write(disk + " " + source + " " + dest + "\n");
			} else if (disk == 2) {
				moveTower(disk - 2, source, spare1, spare2, spare3, dest);
				myfile.write((disk - 1) + " " + source + " " + spare2 + "\n");
				myfile.write(disk + " " + source + " " + dest + "\n");
				myfile.write((disk - 1) + " " + spare2 + " " + dest + "\n");
				moveTower(disk - 2, spare1, dest, source, spare2, spare3);
			} else {
				moveTower(disk - 3, source, spare1, spare2, spare3, dest);
				myfile.write((disk - 2) + " " + source + " " + spare2 + "\n");
				myfile.write((disk - 1) + " " + source + " " + spare3 + "\n");
				myfile.write(disk + " " + source + " " + dest + "\n");
				myfile.write((disk - 1) + " " + spare3 + " " + dest + "\n");
				myfile.write((disk - 2) + " " + spare2 + " " + dest + "\n");
				moveTower(disk - 3, spare1, dest, source, spare2);
			}	
		} catch (Exception e){
			System.out.println("Error: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("Invalid Arguments");
			System.exit(0);
		}

		try{
			
			int pegs = Integer.parseInt(args[0]);
			int disks = Integer.parseInt(args[1]);
			outfile = args[2];

			if (pegs < 3 || pegs > 5) {
				System.out.println("Info: Invalid number of pegs: " + pegs);
				System.exit(0);
			}

			if (disks < 1) {
				System.out.println("Info: Invalid number of disks: " + disks);
				System.exit(0);
			}

			// kept condition by testing the cases
			if ((pegs == 3 && disks > 20) || (pegs == 4 && disks > 39) || (pegs == 5 && disks > 40)) {
				System.out.println("Warning: A large text file will be generated!");
			}

			String info = "";
			// kept condition by testing the cases
			if ((pegs == 3 && disks > 27) || (pegs == 4 && disks > 51) || (pegs == 5 && disks > 53)) {
				info = "(It may take longer time.)";				
			}

			System.out.println("Process Begin.\nPlease wait..." + info);

			File file = new File(outfile);
			file.createNewFile(); // create the new file only it does not exist
			
			myfile = new FileWriter(outfile);

			if (pegs == 3) {
				moveTower(disks,'A','C','B');
			} else if (pegs == 4) {
				moveTower(disks,'A','D','B','C');
			} else if (pegs == 5) {
				moveTower(disks,'A','E','B','C','D');
			}

			myfile.close();

			System.out.println("Process Completed.");

		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
	}
}