package com.mys.ubs.endofdaypositioncalculation.calculation;

import java.io.IOException;

public class Main {
		public static void main(String[] args) {
			EndOfDayPositionCalculator calculator = new EndOfDayPositionCalculator();
			calculator.calculateEndOfDayPositions();
			try {
				calculator.writeToCSV();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}


}
