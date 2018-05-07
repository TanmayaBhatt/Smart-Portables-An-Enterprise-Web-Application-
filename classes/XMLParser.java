import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParser extends DefaultHandler {
    HashMap<String, SmartWatches> smartWatchesMap = new HashMap<>();
    HashMap<String, Speakers> speakersMap = new HashMap<>();
    HashMap<String, HeadPhones> headPhonesMap = new HashMap<>();
    HashMap<String, Phones> phonesMap = new HashMap<>();
    HashMap<String, Laptops> laptopsMap = new HashMap<>();
    HashMap<String, ExternalStorage> externalStorageMap = new HashMap<>();
    HashMap<String, Accessories> accessoriesMap = new HashMap<>();
    
    SmartWatches smartWatches;
    Speakers speakers;
    HeadPhones headPhones;
    Phones phones;
    Laptops laptops;
    ExternalStorage externalStorage;
    Accessories accessory;
    String elementValueRead;
    String currentElement;
    String currentProductId;
    String currentRetailer;
    
    
    public XMLParser(String consoleXmlFileName) {
    	System.out.println(consoleXmlFileName);
        parseDocument(consoleXmlFileName);
    }
    
    public HashMap<String, SmartWatches> getSmartWatchesMap() {
		return smartWatchesMap;
	}
    
    public HashMap<String, Speakers> getSpeakersMap() {
		return speakersMap;
	}

	public HashMap<String, HeadPhones> getHeadPhonesMap() {
		return headPhonesMap;
	}

	public HashMap<String, Phones> getPhonesMap() {
		return phonesMap;
	}

	public HashMap<String, Laptops> getLaptopsMap() {
		return laptopsMap;
	}

	public HashMap<String, ExternalStorage> getExternalStorageMap() {
		return externalStorageMap;
	}
	
	public HashMap<String, Accessories> getAccessoriesMap(){
		return accessoriesMap;
	}

	private void parseDocument(String consoleXmlFileName) {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(consoleXmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfig error");
        } catch (SAXException e) {
            System.out.println("SAXException : xml not well formed");
        } catch (IOException e) {
        	System.out.println(e.getMessage());
            System.out.println("IO error");
        }
    }

    @Override
    public void startElement(String str1, String str2, String elementName, Attributes attributes) throws SAXException {
    	if(elementName.equalsIgnoreCase("smartwatch")){
    		smartWatches = new SmartWatches();
    		currentElement = "smartwatch";
    		String key = attributes.getValue("id");
    		currentProductId = "SW"+key;
    		smartWatchesMap.put("SW"+key, smartWatches);
    		smartWatches.setProductId("SW"+key);
    		currentRetailer = attributes.getValue("retailer");
    		smartWatches.setCompanyName(currentRetailer);
            
    	}
    	else if(elementName.equalsIgnoreCase("speaker")){
    		speakers = new Speakers();
    		currentElement = "speakers";
    		String key = attributes.getValue("id");
    		currentProductId = "S"+key;
    		speakersMap.put("S"+key, speakers);
    		speakers.setProductId("S"+key);
    		currentRetailer = attributes.getValue("retailer");
    		speakers.setCompanyName(currentRetailer);
    	}
    	else if(elementName.equalsIgnoreCase("accessory")){
    		accessory = new Accessories();
    		String key = attributes.getValue("id");
    		accessory.setParentProductId(currentProductId);
    		key = "A" + key + currentProductId;
    		accessoriesMap.put(key, accessory);
    		accessory.setProductId(key);
    		accessory.setCompanyName(currentRetailer);
    		currentElement = "accessory";
    	}
    	else if(elementName.equalsIgnoreCase("headphone")){
    		headPhones = new HeadPhones();
    		currentElement = "headphones";
    		String key = attributes.getValue("id");
    		currentProductId = "HP"+key;
    		headPhonesMap.put("HP"+key, headPhones);
    		headPhones.setProductId("HP"+key);
    		currentRetailer = attributes.getValue("retailer");
    		headPhones.setCompanyName(currentRetailer);
    	}
    	else if(elementName.equalsIgnoreCase("phone")){
    		phones = new Phones();
    		currentElement = "phones";
    		String key = attributes.getValue("id");
    		currentProductId = "P"+key;
    		phonesMap.put("P"+key, phones);
    		phones.setProductId("P"+key);
    		currentRetailer = attributes.getValue("retailer");
    		phones.setCompanyName(currentRetailer);
    	}
    	else if(elementName.equalsIgnoreCase("laptop")){
    		laptops = new Laptops();
    		currentElement = "laptops";
    		String key = attributes.getValue("id");
    		currentProductId = "L"+key;
    		laptopsMap.put("L"+key, laptops);
    		laptops.setProductId("L"+key);
    		currentRetailer = attributes.getValue("retailer");
    		laptops.setCompanyName(currentRetailer);
    	}
    	else if(elementName.equalsIgnoreCase("externalstorage")){
    		externalStorage = new ExternalStorage();
    		currentElement = "externalstorage";
    		String key = attributes.getValue("id");
    		currentProductId = "ES"+key;
    		externalStorageMap.put("ES"+key, externalStorage);
    		externalStorage.setProductId("ES"+key);
    		currentRetailer = attributes.getValue("retailer");
    		externalStorage.setCompanyName(currentRetailer);
    	}
    }
    @Override
    public void endElement(String str1, String str2, String element) throws SAXException {
        if (element.equals("productname")) {
        	if(currentElement.equalsIgnoreCase("smartwatch")){
        		smartWatches.setProductName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("speakers")){
        		speakers.setProductName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("headphones")){
        		headPhones.setProductName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("phones")){
        		phones.setProductName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("laptops")){
        		laptops.setProductName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("externalstorage")){
        		externalStorage.setProductName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("accessory")){
        		accessory.setProductName(elementValueRead);
        	}
        }
        else if (element.equalsIgnoreCase("image")) {
        	if(currentElement.equalsIgnoreCase("smartwatch")){
        		smartWatches.setImageName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("speakers")){
        		speakers.setImageName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("headphones")){
        		headPhones.setImageName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("phones")){
        		phones.setImageName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("laptops")){
        		laptops.setImageName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("externalstorage")){
        		externalStorage.setImageName(elementValueRead);
        	}
        	else if(currentElement.equalsIgnoreCase("accessory")){
        		accessory.setImageName(elementValueRead);
        	}
        }

        else if(element.equalsIgnoreCase("price")){
        	if(currentElement.equalsIgnoreCase("smartwatch")){
        		smartWatches.setPrice(Double.parseDouble(elementValueRead));
        	}
        	else if(currentElement.equalsIgnoreCase("speakers")){
        		speakers.setPrice(Double.parseDouble(elementValueRead));
        	}
        	else if(currentElement.equalsIgnoreCase("headphones")){
        		headPhones.setPrice(Double.parseDouble(elementValueRead));
        	}
        	else if(currentElement.equalsIgnoreCase("phones")){
        		phones.setPrice(Double.parseDouble(elementValueRead));
        	}
        	else if(currentElement.equalsIgnoreCase("laptops")){
        		laptops.setPrice(Double.parseDouble(elementValueRead));
        	}
        	else if(currentElement.equalsIgnoreCase("externalstorage")){
        		externalStorage.setPrice(Double.parseDouble(elementValueRead));
        	}
        	else if(currentElement.equalsIgnoreCase("accessory")){
        		accessory.setPrice(Double.parseDouble(elementValueRead));
        	}
        }
            else if(element.equalsIgnoreCase("sale")){
                if(currentElement.equalsIgnoreCase("smartwatch")){
                    smartWatches.setSale(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("speakers")){
                    speakers.setSale(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("headphones")){
                    headPhones.setSale(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("phones")){
                    phones.setSale(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("laptops")){
                    laptops.setSale(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("externalstorage")){
                    externalStorage.setSale(elementValueRead);
                }
            }
        
            else if(element.equalsIgnoreCase("rebate")){
                if(currentElement.equalsIgnoreCase("smartwatch")){
                    smartWatches.setRebate(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("speakers")){
                    speakers.setRebate(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("headphones")){
                    headPhones.setRebate(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("phones")){
                    phones.setRebate(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("laptops")){
                    laptops.setRebate(elementValueRead);
                }
                else if(currentElement.equalsIgnoreCase("externalstorage")){
                    externalStorage.setRebate(elementValueRead);
                }
            }
        
            else if(element.equalsIgnoreCase("items")){
                if(currentElement.equalsIgnoreCase("smartwatch")){
                    smartWatches.setItem(Double.parseDouble(elementValueRead));
                }
                else if(currentElement.equalsIgnoreCase("speakers")){
                    speakers.setItem(Double.parseDouble(elementValueRead));
                }
                else if(currentElement.equalsIgnoreCase("headphones")){
                    headPhones.setItem(Double.parseDouble(elementValueRead));
                }
                else if(currentElement.equalsIgnoreCase("phones")){
                    phones.setItem(Double.parseDouble(elementValueRead));
                }
                else if(currentElement.equalsIgnoreCase("laptops")){
                    laptops.setItem(Double.parseDouble(elementValueRead));
                }
                else if(currentElement.equalsIgnoreCase("externalstorage")){
                    externalStorage.setItem(Double.parseDouble(elementValueRead));
                }
                
            }
        
        return;
    }

    @Override
    public void characters(char[] content, int begin, int end) throws SAXException {
        elementValueRead = new String(content, begin, end);
    }

}
