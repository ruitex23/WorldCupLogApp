package main.java.utils;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import main.java.domains.Player;

public class WorldCupUtils {

	private static HashMap<String, String> countriesHM = new LinkedHashMap<String, String>();
	
	private static String OS = System.getProperty("os.name").toLowerCase();

	public static final String LAST_32_PHASE = "Last 32";
	public static final String LAST_16_PHASE = "Last 16";
	public static final String QUARTER_FINALS_PHASE = "Quarter-Finals";
	public static final String SEMI_FINALS_PHASE = "Semi-Finals";
	public static final String FINAL_PHASE = "Final";
	public static final String THIRD_FORTH_PHASE = "Third and Forth";

	//Countries
	public static final String ARGENTINA_CODE = "ARG";
	public static final String ARGENTINA_CODE_2L = "ar";
	public static final String ARGENTINA_DESC = "Argentina";
	public static final String AUSTRIA_CODE = "AUT";
	public static final String AUSTRIA_CODE_2L = "at";
	public static final String AUSTRIA_DESC = "Austria";
	public static final String AUSTRALIA_CODE = "AUS";
	public static final String AUSTRALIA_CODE_2L = "au";
	public static final String AUSTRALIA_DESC = "Australia";
	public static final String BELGIUM_CODE = "BEL";
	public static final String BELGIUM_CODE_2L = "be";
	public static final String BELGIUM_DESC = "Belgium";
	public static final String BOLIVIA_CODE = "BOL";
	public static final String BOLIVIA_CODE_2L = "bo";
	public static final String BOLIVIA_DESC = "Bolivia";
	public static final String BRASIL_CODE = "BRA";
	public static final String BRASIL_CODE_2L = "br";
	public static final String BRASIL_DESC = "Brazil";
	public static final String CZECHREP_CODE = "CZE";
	public static final String CZECHREP_CODE_2L = "cz";
	public static final String CZECHREP_DESC = "Czech Republic";
	public static final String CYPRUS_CODE = "CYP";
	public static final String CYPRUS_CODE_2L = "cy";
	public static final String CYPRUS_DESC = "Cyprus";
	public static final String COLOMBIA_CODE = "COL";
	public static final String COLOMBIA_CODE_2L = "co";
	public static final String COLOMBIA_DESC = "Colombia";
	public static final String COSTARICA_CODE = "CRI";
	public static final String COSTARICA_CODE_2L = "cr";
	public static final String COSTARICA_DESC = "Costa Rica";
	public static final String DENMARK_CODE = "DNK";
	public static final String DENMARK_CODE_2L = "dk";
	public static final String DENMARK_DESC = "Denmark";
	public static final String ECUADOR_CODE = "ECU";
	public static final String ECUADOR_CODE_2L = "ec";
	public static final String ECUADOR_DESC = "Ecuador";
	public static final String EGYPT_CODE = "EGY";
	public static final String EGYPT_CODE_2L = "eg";
	public static final String EGYPT_DESC = "Egypt";
	public static final String FRANCE_CODE = "FRA";
	public static final String FRANCE_CODE_2L = "fr";
	public static final String FRANCE_DESC = "France";
	public static final String FINLAND_CODE = "FIN";
	public static final String FINLAND_CODE_2L = "fi";
	public static final String FINLAND_DESC = "Finland";
	public static final String GERMANY_CODE = "DEU";
	public static final String GERMANY_CODE_2L = "de";
	public static final String GERMANY_DESC = "Germany";
	public static final String GREECE_CODE = "GRE";
	public static final String GREECE_CODE_2L = "gr";
	public static final String GREECE_DESC = "Greece";
	public static final String GUATEMALA_CODE = "GUA";
	public static final String GUATEMALA_CODE_2L = "gu";
	public static final String GUATEMALA_DESC = "Guatemala";
	public static final String ITALY_CODE = "ITA";
	public static final String ITALY_CODE_2L = "it";
	public static final String ITALY_DESC = "Italy";
	public static final String JAPAN_CODE = "JPN";
	public static final String JAPAN_CODE_2L = "jp";
	public static final String JAPAN_DESC = "Japan";
	public static final String JORDAN_CODE = "JOR";
	public static final String JORDAN_CODE_2L = "jo";
	public static final String JORDAN_DESC = "Jordan";
	public static final String LUXEMBOURG_CODE = "LUX";
	public static final String LUXEMBOURG_CODE_2L = "lu";
	public static final String LUXEMBOURG_DESC = "Luxembourg";
	public static final String MEXICO_CODE = "MEX";
	public static final String MEXICO_CODE_2L = "mx";
	public static final String MEXICO_DESC = "Mexico";
	public static final String NETHERLANDS_CODE = "NLD";
	public static final String NETHERLANDS_CODE_2L = "nl";
	public static final String NETHERLANDS_DESC = "Netherlands";
	public static final String PORTUGAL_CODE = "POR";
	public static final String PORTUGAL_CODE_2L = "pt";
	public static final String PORTUGAL_DESC = "Portugal";
	public static final String PERU_CODE = "PER";
	public static final String PERU_CODE_2L = "pe";
	public static final String PERU_DESC = "Peru";
	public static final String SWEDEN_CODE = "SWE";
	public static final String SWEDEN_CODE_2L = "se";
	public static final String SWEDEN_DESC = "Sweden";
	public static final String SPAIN_CODE = "ESP";
	public static final String SPAIN_CODE_2L = "es";
	public static final String SPAIN_DESC = "Spain";
	public static final String SWITZERLAND_CODE = "CHE";
	public static final String SWITZERLAND_CODE_2L = "ch";
	public static final String SWITZERLAND_DESC = "Switzerland";
	public static final String SOUTHKOREA_CODE = "KOR";
	public static final String SOUTHKOREA_CODE_2L = "kr";
	public static final String SOUTHKOREA_DESC = "South Korea";
	public static final String TURKEY_CODE = "TUR";
	public static final String TURKEY_CODE_2L = "tr";
	public static final String TURKEY_DESC = "Turkey";
	public static final String US_CODE = "USA";
	public static final String US_CODE_2L = "us";
	public static final String US_DESC = "USA";
	public static final String VIETNAM_CODE = "VNM";
	public static final String VIETNAM_CODE_2L = "vn";
	public static final String VIETNAM_DESC = "Vietnam";
	public static final String VENEZUELA_CODE = "VEN";
	public static final String VENEZUELA_CODE_2L = "ve";
	public static final String VENEZUELA_DESC = "Venezuela";
	public static final String UK_CODE = "GBR";
	public static final String UK_CODE_2L = "GB";
	public static final String UK_DESC = "Great Britain";



	public static void sortPlayers(List<Player> playerList) {
		System.out.println("Sorting players...");
		Collections.sort(playerList, new Comparator<Player>() {
			public int compare(Player p1, Player p2) {
				int res =  p1.getLastName().compareToIgnoreCase(p2.getLastName());
				if (res != 0)
					return res;
				return p1.getFirstName().compareToIgnoreCase(p2.getFirstName());
			}
		});
	}

	public static void initializeCountries() {
		System.out.println("Initializing countries...");
		countriesHM.put(WorldCupUtils.ARGENTINA_CODE_2L, WorldCupUtils.ARGENTINA_DESC);
		countriesHM.put(WorldCupUtils.AUSTRALIA_CODE_2L, WorldCupUtils.AUSTRALIA_DESC);
		countriesHM.put(WorldCupUtils.AUSTRIA_CODE_2L, WorldCupUtils.AUSTRIA_DESC);
		countriesHM.put(WorldCupUtils.BELGIUM_CODE_2L, WorldCupUtils.BELGIUM_DESC);
		countriesHM.put(WorldCupUtils.BOLIVIA_CODE_2L, WorldCupUtils.BOLIVIA_DESC);
		countriesHM.put(WorldCupUtils.BRASIL_CODE_2L, WorldCupUtils.BRASIL_DESC);
		countriesHM.put(WorldCupUtils.COLOMBIA_CODE_2L, WorldCupUtils.COLOMBIA_DESC);
		countriesHM.put(WorldCupUtils.COSTARICA_CODE_2L, WorldCupUtils.COSTARICA_DESC);
		countriesHM.put(WorldCupUtils.CYPRUS_CODE_2L, WorldCupUtils.CYPRUS_DESC);
		countriesHM.put(WorldCupUtils.CZECHREP_CODE_2L, WorldCupUtils.CZECHREP_DESC);
		countriesHM.put(WorldCupUtils.DENMARK_CODE_2L, WorldCupUtils.DENMARK_DESC);
		countriesHM.put(WorldCupUtils.ECUADOR_CODE_2L, WorldCupUtils.ECUADOR_DESC);
		countriesHM.put(WorldCupUtils.EGYPT_CODE_2L, WorldCupUtils.EGYPT_DESC);
		countriesHM.put(WorldCupUtils.FINLAND_CODE_2L, WorldCupUtils.FINLAND_DESC);
		countriesHM.put(WorldCupUtils.FRANCE_CODE_2L, WorldCupUtils.FRANCE_DESC);
		countriesHM.put(WorldCupUtils.GERMANY_CODE_2L, WorldCupUtils.GERMANY_DESC);
		countriesHM.put(WorldCupUtils.GREECE_CODE_2L, WorldCupUtils.GREECE_DESC);
		countriesHM.put(WorldCupUtils.GUATEMALA_CODE_2L, WorldCupUtils.GUATEMALA_DESC);
		countriesHM.put(WorldCupUtils.ITALY_CODE_2L, WorldCupUtils.ITALY_DESC);
		countriesHM.put(WorldCupUtils.JAPAN_CODE_2L, WorldCupUtils.JAPAN_DESC);
		countriesHM.put(WorldCupUtils.JORDAN_CODE_2L, WorldCupUtils.JORDAN_DESC);
		countriesHM.put(WorldCupUtils.LUXEMBOURG_CODE_2L, WorldCupUtils.LUXEMBOURG_DESC);
		countriesHM.put(WorldCupUtils.MEXICO_CODE_2L, WorldCupUtils.MEXICO_DESC);
		countriesHM.put(WorldCupUtils.NETHERLANDS_CODE_2L, WorldCupUtils.NETHERLANDS_DESC);
		countriesHM.put(WorldCupUtils.PERU_CODE_2L, WorldCupUtils.PERU_DESC);
		countriesHM.put(WorldCupUtils.PORTUGAL_CODE_2L, WorldCupUtils.PORTUGAL_DESC);
		countriesHM.put(WorldCupUtils.SOUTHKOREA_CODE_2L, WorldCupUtils.SOUTHKOREA_DESC);
		countriesHM.put(WorldCupUtils.SPAIN_CODE_2L, WorldCupUtils.SPAIN_DESC);
		countriesHM.put(WorldCupUtils.SWEDEN_CODE_2L, WorldCupUtils.SWEDEN_DESC);
		countriesHM.put(WorldCupUtils.SWITZERLAND_CODE_2L, WorldCupUtils.SWITZERLAND_DESC);
		countriesHM.put(WorldCupUtils.TURKEY_CODE_2L, WorldCupUtils.TURKEY_DESC);
		countriesHM.put(WorldCupUtils.UK_CODE_2L, WorldCupUtils.UK_DESC);
		countriesHM.put(WorldCupUtils.US_CODE_2L, WorldCupUtils.US_DESC);
		countriesHM.put(WorldCupUtils.VENEZUELA_CODE_2L, WorldCupUtils.VENEZUELA_DESC);
		countriesHM.put(WorldCupUtils.VIETNAM_CODE_2L, WorldCupUtils.VIETNAM_DESC);
	}

	public static String getCountryCodeByDescription(String description) {
		System.out.println("Description: "+ description);
		for(String s:countriesHM.keySet()) {
			System.out.println("S -> " + s);
			if(countriesHM.get(s).compareToIgnoreCase(description) == 0) {
				System.out.println("Returning S:" + s);
				return s;
			}
		}
		return "";
	}

	public static String getCountryDescription(String code) {
		if(countriesHM.containsKey(code)) {
			return countriesHM.get(code);
		}
		return "";
	}
	
	public static String detectOS() {
		if (isWindows()) {
			return "WIN";
		} else if (isMac()) {
			return "MAC";
		} else if (isUnix()) {
			return "UNIX";
		} else if (isSolaris()) {
			return "SOL";
		} else {
			return "NOS";
		}
	}
	
	private static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	private static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	private static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

	}

	private static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}

}
