package com.atguigu.boot.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LocalUtil {
	//各地区xml文件路径
	private static final String LOCAL_LIST_PATH = "src/main/resources/templates/config/LocList.xml";
	//所有国家名称List
	private static final List<String> COUNTRY_REGION = new ArrayList<String>();
	private static LocalUtil localutil;
	private SAXReader reader;
	private Document document;
	private Element rootElement;        //根元素

	//初始化
	private LocalUtil() {
		//1.读取
		reader = new SAXReader();
		try {
			document = reader.read(LOCAL_LIST_PATH);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		//2.获得根元素
		rootElement = document.getRootElement();
		//3.初始化所有国家名称列表
		Iterator it = rootElement.elementIterator();
		Element ele = null;
		while (it.hasNext()) {
			ele = (Element) it.next();
			COUNTRY_REGION.add(ele.attributeValue("Name"));
		}
	}

	/**
	 * @author licheng
	 * @TODO 功能：	获取所有国家名称
	 * @time 2016-8-26 上午9:02:05
	 * @return String[]
	 */
	public List<String> getCountry() {
		return COUNTRY_REGION;
	}

	/**
	 * @param countryName 国家名，从getCountry()从取出
	 * @author licheng
	 * @TODO 功能：	根据国家名获取该国所有省份
	 * @time 2016-8-26 上午9:07:21
	 * @return List<Element>
	 */
	private List<Element> provinces(String countryName) {
		Iterator it = rootElement.elementIterator();
		List<Element> provinces = new ArrayList<Element>();
		Element ele = null;
		while (it.hasNext()) {
			ele = (Element) it.next();
			COUNTRY_REGION.add(ele.attributeValue("Name"));
			if (ele.attributeValue("Name").equals(countryName)) {
				provinces = ele.elements();
				break;
			}
		}
		return provinces;
	}

	/**
	 * @param countryName 国家名，从getCountry()从取出
	 * @author licheng
	 * @TODO 功能：	根据国家名获取该国所有省份
	 * @time 2016-8-26 上午9:07:21
	 * @return List<Element>
	 */
	public List<String> getProvinces(String countryName) {
		List<Element> tmp = this.provinces(countryName);
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < tmp.size(); i++) {
			list.add(tmp.get(i).attributeValue("Name"));
		}
		return list;
	}

	/**
	 * @param
	 * @param provinceName
	 * @return
	 * @author licheng
	 * @TODO 功能：根据国家名和省份名，获取该省城市名列表
	 * @time 2016-8-26 上午9:15:24
	 */
	private List<Element> cities(String countryName, String provinceName) {
		List<Element> provinces = this.provinces(countryName);
		List<Element> cities = new ArrayList<Element>();
		if (provinces == null || provinces.size() == 0) {        //没有这个城市
			return cities;
		}

		for (int i = 0; i < provinces.size(); i++) {
			if (provinces.get(i).attributeValue("Name").equals(provinceName)) {
				cities = provinces.get(i).elements();
				break;
			}
		}
		return cities;
	}

	/**
	 * @param countryName
	 * @param provinceName
	 * @author licheng
	 * @TODO 功能：根据国家名和省份名获取城市列表
	 * @time 2016-8-26 下午4:55:55
	 * @return List<String>
	 */
	public List<String> getCities(String countryName, String provinceName) {
		List<Element> tmp = this.cities(countryName, provinceName);
		List<String> cities = new ArrayList<String>();
		for (int i = 0; i < tmp.size(); i++) {
			cities.add(tmp.get(i).attributeValue("Name"));
		}
		return cities;
	}


	/**
	 * @param
	 * @param provinceName
	 * @return
	 * @author licheng
	 * @TODO 功能：根据国家名和省份名和城市名，获取该城市县级名称列表
	 * @time 2016-8-26 上午10:34:44
	 */
	private List<Element> county(String countryName, String provinceName, String city) {
		//获取城市名称
		List<Element> cities1 = this.cities(countryName, provinceName);
		List<Element> cities = new ArrayList<Element>();
		if (cities1 == null || cities1.size() == 0) {        //没有这个县
			return cities;
		}

		for (int i = 0; i < cities1.size(); i++) {
			if (cities1.get(i).attributeValue("Name").equals(city)) {
				cities = cities1.get(i).elements();
				break;
			}
		}
		return cities;
	}

	/**
	 * @param countryName
	 * @param provinceName
	 * @return List<String>
	 * @author licheng
	 * @TODO 功能：根据国家,省份和城市名获取县级名称
	 * @time 2016-8-26 下午4:55:55
	 */
	public List<String> getcounty(String countryName, String provinceName, String city) {
		List<Element> tmp = this.county(countryName, provinceName, city);
		List<String> cities = new ArrayList<String>();
		for (int i = 0; i < tmp.size(); i++) {
			cities.add(tmp.get(i).attributeValue("Name"));
		}
		return cities;
	}


	public static LocalUtil getInstance() {
		if (localutil == null) {
			localutil = new LocalUtil();
		}
		return localutil;
	}

	public static void main(String[] args) {
			LocalUtil lu =  LocalUtil.getInstance();
			List<String> list = 	lu.getCities("中国", "北京");
			for(int i=0; i<list.size(); i++){
				System.out.print(list.get(i) + " ");
			}


		System.out.println("-------------------------------");

		LocalUtil lu2 =  LocalUtil.getInstance();
		List<String> list2 = 	lu.getProvinces("中国");
		int j=0;
		for(int i=0; i<list2.size(); i++){
			j++;
			System.out.print(list2.get(i) + " "+"\n");
		}


		System.out.println("--------------------------------------");




		LocalUtil lu3 = LocalUtil.getInstance();
			List<String> str = new ArrayList<>();
			List<String> list3 = lu.getcounty("中国", "浙江", "杭州");
			for (int i = 0; i < list3.size(); i++) {
				str.add(list3.get(i) + " ");
			}

		System.out.println("--------------------------------------");


		LocalUtil lu4 =  LocalUtil.getInstance();
		List<String> list4 = 	lu.getProvinces("美国");

		for(int i=0; i<list4.size(); i++){
			System.out.print(list4.get(i) + " "+"\n");
		}

//		@Test
//		public void test1(){
//			LocalUtil lu =  LocalUtil.getInstance();
//			List<String> list = 	lu.getCities("中国", "北京");
//			for(int i=0; i<list.size(); i++){
//				System.out.print(list.get(i) + " ");
//			}
//		}
//		@Test
//		public void test2(){
//			LocalUtil lu =  LocalUtil.getInstance();
//			List<String> list = 	lu.getProvinces("中国");
//			int j=0;
//			for(int i=0; i<list.size(); i++){
//				j++;
//				System.out.print(list.get(i) + " "+"\n");
//			}
//			System.out.println(j);
//		}
//		@Test
//		public  void test3(){
//			LocalUtil lu = LocalUtil.getInstance();
//			List<String> str = new ArrayList<>();
//			List<String> list = lu.getcounty("中国", "浙江", "杭州");
//			for (int i = 0; i < list.size(); i++) {
//				str.add(list.get(i) + " ");
//			}
//			System.out.println(str);
//		}
//	}

	}
}
