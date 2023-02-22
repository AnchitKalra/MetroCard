package com.example.geektrust;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


public class Main {


    public static void main(String[] args) {
        /*
        Sample code to read from file passed as command line argument*/
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            MetroCard metroCard[] = new MetroCard[1000];
            
            int countOfCards = 0;
            Set<String> set = new HashSet<>();
            while (sc.hasNextLine()) {
               //Add your code here to process input commands
                String s = sc.nextLine();
                String input[] = s.split(" ");

                if(input[0].equalsIgnoreCase("balance")) {
                    metroCard[countOfCards] = new MetroCard();
                    Map<String, Integer> stationListMap = new HashMap<>();
                    Map<String, Integer> discountMap = new HashMap<>();
                    Map<String, Map<String, Integer>> map = new HashMap<>();
                    Map<String, Integer> map1 = new HashMap<>();
                    Map<String, Integer> map2 = new HashMap<>();
                    map.put("CENTRAL", map1);
                    map.put("AIRPORT", map2);
                    discountMap.put("CENTRAL", 0);
                    discountMap.put("AIRPORT", 0);
                    stationListMap.put("CENTRAL", 0);
                    stationListMap.put("AIRPORT", 0);
                    Category category = new Category();
                    metroCard[countOfCards].setCategoryType(category);
                    metroCard[countOfCards].getCategoryType().setCount(map);
                    Station station = new Station();
                    metroCard[countOfCards].setStationType(station);
                    metroCard[countOfCards].getStationType().setStationTypeDiscount(discountMap);
                    metroCard[countOfCards].getStationType().setStationTypeCollection(stationListMap);
                    metroCard[countOfCards].setMetroCardNumber(input[1]);
                    metroCard[countOfCards].setBalance(Integer.valueOf(input[2]));
                    countOfCards++;
                } else if (input[0].equalsIgnoreCase("CHECK_IN")) {

                    for(int i = 0; i < countOfCards; i++) {
                        if(metroCard[i].getMetroCardNumber().equalsIgnoreCase(input[1])) {


                         metroCard[i].getStationType().getStationTypeCollection().put(input[1], 0);
                         if(input[3].equalsIgnoreCase("central")) {
                             Map<String, Map<String, Integer>> map = metroCard[i].getCategoryType().getCount();
                             Map<String, Integer> map1 = map.get("CENTRAL");
                             if(map1.containsKey(input[2])) {
                                 map1.put(input[2], map1.get(input[2]) + 1);
                             }
                             else {
                                 map1.put(input[2], 1);

                             }
                             map.put("CENTRAL", map1);
                             metroCard[i].getCategoryType().setCount(map);
                         }
                         else {
                             Map<String, Map<String, Integer>> map = metroCard[i].getCategoryType().getCount();
                             Map<String, Integer> map1 = map.get("AIRPORT");
                             if(map1.containsKey(input[2])) {
                                 map1.put(input[2], map1.get(input[2]) + 1);
                             }
                             else {
                                 map1.put(input[2], 1);
                             }
                             map.put("AIRPORT", map1);
                             metroCard[i].getCategoryType().setCount(map);
                         }


                          int balance = metroCard[i].getBalance();
                          if(metroCard[i].getCategoryType().getCount().get(input[3]).containsKey("ADULT"))
                          {
                              int cost = 0;
                              int discount = 0;
                              if(input[3].equalsIgnoreCase("central")) {
                               Map<String, Integer> integerMap = metroCard[i].getCategoryType().getCount().get("CENTRAL");
                               Map<String, Integer> integerMap1 = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                               int count = integerMap.get("ADULT") + ((integerMap1.get("ADULT")== null) ?0 : integerMap1.get("ADULT"));

                                  if(count % 2 == 0) {
                                      if(balance < 100) {
                                          int a = 100 - balance;
                                          int serve = (2 * a) / 100;
                                          cost += serve + 100;
                                          metroCard[i].setBalance(0);
                                          discount = 100;
                                      }
                                      else {
                                          cost =  100;
                                          metroCard[i].setBalance(balance - 100);
                                          discount = 100;

                                      }
                                  }
                                  else {

                                      if (balance < 200) {
                                          int a = (200 - balance);
                                          int serve = (2 * a) / 100;
                                          cost = serve + 200;
                                          metroCard[i].setBalance(0);
                                      } else {
                                          cost = 200;
                                          metroCard[i].setBalance(balance - 200);
                                      }
                                  }
                                  Map<String, Integer> map = metroCard[i].getStationType().getStationTypeCollection();
                                  int collection = map.get("CENTRAL");
                                  map.put("CENTRAL", collection + cost);
                                  Map<String, Integer> map2 = metroCard[i].getStationType().getStationTypeDiscount();
                                  int prevDiscount = map2.get("CENTRAL");
                                  map2.put("CENTRAL", prevDiscount + discount);
                                  metroCard[i].getStationType().setStationTypeCollection(map);
                                  metroCard[i].getStationType().setStationTypeDiscount(map2);
                              }
                              else {
                                  Map<String, Integer> integerMap = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                                  Map<String, Integer> integerMap1 = metroCard[i].getCategoryType().getCount().get("CENTRAL");
                                  int count = 0;
                                  if(integerMap1.get("ADULT") != null) {
                                       count = integerMap.get("ADULT") + integerMap1.get("ADULT");
                                  }else {
                                      count = integerMap.get("ADULT");
                                  }

                                  if(count % 2 == 0) {
                                      if(balance < 100) {
                                          int a = 100 - balance;
                                          int serve = (2 * a) / 100;
                                          cost += serve + 100;
                                          metroCard[i].setBalance(0);
                                          discount = 100;
                                      }
                                      else {
                                          cost =  100;
                                          metroCard[i].setBalance(balance - 100);
                                          discount = 100;

                                      }
                                  }
                                  else {
                                      if(balance < 200) {
                                          int a = (200 - balance);
                                          int serve = (2  * a) / 100;
                                          cost += serve + 200;

                                          metroCard[i].setBalance(0);
                                      }
                                      else {
                                          cost = 200;
                                          metroCard[i].setBalance(balance - cost);
                                      }
                                  }
                                  Map<String, Integer> map = metroCard[i].getStationType().getStationTypeCollection();
                                  int collection = map.get("AIRPORT");
                                  map.put("AIRPORT", collection + cost);
                                  Map<String, Integer> map2 = metroCard[i].getStationType().getStationTypeDiscount();
                                  int prevDiscount = map2.get("AIRPORT");
                                  map2.put("AIRPORT", prevDiscount + discount);
                                  metroCard[i].getStationType().setStationTypeCollection(map);
                                  metroCard[i].getStationType().setStationTypeDiscount(map2);
                              }

                          }
                          else if(metroCard[i].getCategoryType().getCount().get(input[3]).containsKey("KID")) {
                              int cost = 0;
                              int discount = 0;
                              if(input[3].equalsIgnoreCase("central"))  {
                                  Map<String, Integer> integerMap = metroCard[i].getCategoryType().getCount().get("CENTRAL");
                                  Map<String, Integer> integerMap1 = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                                  int count = 0;
                                  if(integerMap1.get("KID") != null) {
                                      count = integerMap.get("KID") + integerMap1.get("KID");
                                  }else {
                                      count = integerMap.get("KID");
                                  }
                                  if(count % 2 == 0) {
                                      if(balance < 25) {
                                          int a = 25 - balance;
                                          int serve = (2 * a) / 100;
                                          cost += serve + 25;
                                          metroCard[i].setBalance(0);
                                          discount = 25;
                                      }
                                      else {
                                          cost = 25;
                                          metroCard[i].setBalance(balance - 25);
                                          discount = 25;

                                      }
                                  }
                                  else {

                                      if (balance < 50) {
                                          int a = (50 - balance);
                                          int serve = (2 * a) / 100;
                                          cost += serve + 50;
                                          metroCard[i].setBalance(0);
                                      } else {
                                          cost = 50;
                                          metroCard[i].setBalance(balance - cost);
                                      }
                                  }
                                  Map<String, Integer> map = metroCard[i].getStationType().getStationTypeCollection();
                                  int collection = map.get("CENTRAL");
                                  map.put("CENTRAL", collection + cost);
                                  Map<String, Integer> map2 = metroCard[i].getStationType().getStationTypeDiscount();
                                  int prevDiscount = map2.get("CENTRAL");
                                  map2.put("CENTRAL", prevDiscount + discount);
                                  metroCard[i].getStationType().setStationTypeCollection(map);
                                  metroCard[i].getStationType().setStationTypeDiscount(map2);
                                 // System.out.println("CENTRAL-->" + metroCard[i].getMetroCardNumber() + " " + metroCard[i].getStationType().getStationTypeCollection().get("CENTRAL"));

                              }
                              else {
                                  Map<String, Integer> integerMap = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                                  Map<String, Integer> integerMap1 = metroCard[i].getCategoryType().getCount().get("CENTRAL");
                                  int count = 0;
                                  if(integerMap1.get("KID") != null) {
                                      count = integerMap.get("KID") + integerMap1.get("KID");
                                  }else {
                                      count = integerMap.get("KID");
                                  }
                                  if(count % 2 == 0) {
                                      if(balance < 25) {
                                          int a = 25 - balance;
                                        int serve = (2 * a) / 100;
                                        cost += serve + 25;
                                          metroCard[i].setBalance(0);
                                          discount = 25;
                                      }
                                      else {
                                          cost = 25;
                                          metroCard[i].setBalance(balance - 25);
                                          discount = 25;
                                      }
                                  }
                                  else {
                                      if(balance < 50) {
                                          int a= (50 - balance);
                                        int serve = (2 * a) / 100;
                                        cost += serve + 50;
                                          metroCard[i].setBalance(0);
                                      }
                                      else {
                                          cost = 50;
                                          metroCard[i].setBalance(balance - cost);
                                      }
                                  }
                                  Map<String, Integer> map = metroCard[i].getStationType().getStationTypeCollection();
                                  int collection = map.get("AIRPORT");
                                  map.put("AIRPORT", collection + cost);
                                  Map<String, Integer> map2 = metroCard[i].getStationType().getStationTypeDiscount();
                                  int prevDiscount = map2.get("AIRPORT");
                                  map2.put("AIRPORT", prevDiscount + discount);
                                  metroCard[i].getStationType().setStationTypeCollection(map);
                                  metroCard[i].getStationType().setStationTypeDiscount(map2);
                               //   System.out.println("AIRPORT-->" + metroCard[i].getMetroCardNumber() + " " + metroCard[i].getStationType().getStationTypeCollection().get("AIRPORT"));

                              }

                          }
                          else {
                              int cost = 0;
                              int discount = 0;
                              if(input[3].equalsIgnoreCase("central"))  {
                                  Map<String, Integer> integerMap = metroCard[i].getCategoryType().getCount().get("CENTRAL");
                                  Map<String, Integer> integerMap1 = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                                  int count = 0;
                                  if(integerMap1.get("SENIOR_CITIZEN") != null) {
                                      count = integerMap.get("SENIOR_CITIZEN") + integerMap1.get("SENIOR_CITIZEN");
                                  }else {
                                      count = integerMap.get("SENIOR_CITIZEN");
                                  }
                                  if(count % 2 == 0) {
                                      if(balance < 50) {
                                          int a = 50 - balance;
                                          int serve = (a * 2) / 100;
                                          cost += serve + 50;
                                          metroCard[i].setBalance(0);
                                          discount = 50;
                                      }
                                      else {
                                          cost =  50;
                                          metroCard[i].setBalance(balance - cost);
                                          discount = 50;
                                      }
                                  }
                                  else {

                                      if (balance < 100) {
                                          int a = (100 - balance);
                                          int serve = (2 * a) / 100;
                                          cost += serve + 100;
                                          metroCard[i].setBalance(0);
                                      } else {
                                          cost = 100;
                                          metroCard[i].setBalance(balance - cost);

                                      }
                                  }
                                  Map<String, Integer> map = metroCard[i].getStationType().getStationTypeCollection();
                                  int collection = map.get("CENTRAL");
                                  map.put("CENTRAL", collection + cost);
                                  Map<String, Integer> map2 = metroCard[i].getStationType().getStationTypeDiscount();
                                  int prevDiscount = map2.get("CENTRAL");
                                  map2.put("CENTRAL", prevDiscount + discount);
                                  metroCard[i].getStationType().setStationTypeCollection(map);
                                //  System.out.println("CENTRAL-->" + metroCard[i].getMetroCardNumber() + " " + metroCard[i].getStationType().getStationTypeCollection().get("CENTRAL"));

                              }
                              else {
                                  Map<String, Integer> integerMap = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                                  Map<String, Integer> integerMap1 = metroCard[i].getCategoryType().getCount().get("CENTRAL");

                                  int count = 0;
                                  if(integerMap1.get("SENIOR_CITIZEN") != null) {
                                      count = integerMap.get("SENIOR_CITIZEN") + integerMap1.get("SENIOR_CITIZEN");
                                  }else {
                                      count = integerMap.get("SENIOR_CITIZEN");
                                  }
                                  if(count % 2 == 0) {
                                      if(balance < 50) {
                                          int a = 50 - balance;
                                         int serve = (a * 2) / 100;
                                         cost += serve + 50;
                                          metroCard[i].setBalance(0);
                                          discount = 50;
                                      }
                                      else {
                                          cost =  50;
                                          discount = 50;
                                          metroCard[i].setBalance(balance - cost);
                                      }
                                  }
                                  else {
                                      if(balance < 100) {
                                         int a = (100 - balance);
                                         int serve = (2 * a) / 100;
                                         cost += serve + 100;
                                          metroCard[i].setBalance(0);
                                      }
                                      else {
                                          cost =  100;
                                          metroCard[i].setBalance(balance - cost);

                                      }
                                  }
                                  Map<String, Integer> map = metroCard[i].getStationType().getStationTypeCollection();
                                  int collection = map.get("AIRPORT");
                                  map.put("AIRPORT", collection + cost);
                                  Map<String, Integer> map2 = metroCard[i].getStationType().getStationTypeDiscount();
                                  int prevDiscount = map2.get("AIRPORT");
                                  map2.put("AIRPORT", prevDiscount + discount);
                                  metroCard[i].getStationType().setStationTypeCollection(map);
                                  metroCard[i].getStationType().setStationTypeDiscount(map2);
                               //   System.out.println("AIRPORT-->" + metroCard[i].getMetroCardNumber() + " " + metroCard[i].getStationType().getStationTypeCollection().get("AIRPORT") + " SENIOR->" + metroCard[i].getStationType().getStationTypeDiscount().get("AIRPORT"));
                              }


                          }


                        }
                    }
                }


                if(input[0].equalsIgnoreCase("print_summary")) {
                   Map<String, Integer> map = new HashMap<>();
                   map.put("CENTRAL", 0);
                   map.put("AIRPORT", 0);
                   Map<String, Integer> map1 = new HashMap<>();
                   map1.put("CENTRAL", 0);
                   map1.put("AIRPORT", 0);
                    Map<String, Map<String, Integer>> hashMap2 = new HashMap<>();
                    Map<String, Integer> integerMap = new HashMap<>();
                    Map<String, Integer> integerMap1 = new HashMap<>();

                    List<String> list = new ArrayList<>();
                    list.add("ADULT");
                    list.add("KID");
                    list.add("SENIOR_CITIZEN");
                    for (String x : list) {
                        integerMap.put(x, 0);
                        integerMap1.put(x, 0);
                    }
                    hashMap2.put("CENTRAL", integerMap);
                    hashMap2.put("AIRPORT", integerMap1);
                    for (int i = 0; i < countOfCards; i++) {
                      // System.out.println("BALANCE-->" + metroCard[i].getBalance());
                     Map<String, Integer> hashMap = metroCard[i].getStationType().getStationTypeCollection();
                       Map<String, Integer> hashMap1 = metroCard[i].getStationType().getStationTypeDiscount();
                        for (String x : list) {
                            Map<String, Integer> amp = metroCard[i].getCategoryType().getCount().get("CENTRAL");
                            Map<String, Integer> hmap = hashMap2.get("CENTRAL");
                            int a = metroCard[i].getCategoryType().getCount().get("CENTRAL").get(x) != null ? metroCard[i].getCategoryType().getCount().get("CENTRAL").get(x) : 0;

                          //  System.out.println(x + " >" + a);


                                     //System.out.println(x + "----------->" + b);

                            int c = a + hmap.get(x);
                            hmap.put(x, c);
                            //amp.put(x, c);

                            hashMap2.put("CENTRAL", hmap);


                            Map<String, Integer> amp1 = metroCard[i].getCategoryType().getCount().get("AIRPORT");
                            Map<String, Integer> hmap2 = hashMap2.get("AIRPORT");
                            int y = metroCard[i].getCategoryType().getCount().get("AIRPORT").get(x) != null ? metroCard[i].getCategoryType().getCount().get("AIRPORT").get(x) : 0;


                            int m = y + hmap2.get(x);
                            hmap2.put(x, m);

                            hashMap2.put("AIRPORT", hmap2);


                        }

                       int collection = 0;
                       int discount = 0;

                         collection = hashMap.get("CENTRAL");
                         map.put("CENTRAL", map.get("CENTRAL") + collection);

                         discount = hashMap1.get("CENTRAL");
                         map1.put("CENTRAL", map1.get("CENTRAL") + discount);

                         collection = hashMap.get("AIRPORT");
                         map.put("AIRPORT", map.get("AIRPORT") + collection);

                         discount = hashMap1.get("AIRPORT");
                         map1.put("AIRPORT", map1.get("AIRPORT") + discount);




                   }

                   System.out.println("TOTAL_COLLECTION " + "CENTRAL " + map.get("CENTRAL") + " " + map1.get("CENTRAL"));
                    System.out.println("PASSENGER_TYPE_SUMMARY");
                    //List<Integer> sortedList = new ArrayList<>();
                     list = new ArrayList<>();
                       //System.out.println(x + " " + hashMap2.get("CENTRAL").get(x));
                       int a = hashMap2.get("CENTRAL").get("ADULT");
                       int b = hashMap2.get("CENTRAL").get("KID");
                       int c = hashMap2.get("CENTRAL").get("SENIOR_CITIZEN");
                       if(a >= b) {
                           if(a >= c) {
                               list.add("ADULT");
                               if(b >= c) {
                                   list.add("KID");
                                   list.add("SENIOR_CITIZEN");
                               }
                               else {
                                   list.add("SENIOR_CITIZEN");
                                   list.add("KID");
                               }
                           }
                           else {
                               list.add("SENIOR_CITIZEN");
                               list.add("ADULT");
                               list.add("KID");
                           }
                       }
                       else if(b >= c) {
                           list.add("KID");
                           if(a >= c) {
                               list.add("ADULT");
                               list.add("SENIOR_CITIZEN");
                           }
                           else {
                               list.add("SENIOR_CITIZEN");
                               list.add("ADULT");
                           }
                       }
                       else {
                           list.add("SENIOR_CITIZEN");
                           list.add("KID");
                           list.add("ADULT");
                       }
                       for(String x : list) {
                           if(hashMap2.get("CENTRAL").get(x) != 0)
                           System.out.println(x + " " + hashMap2.get("CENTRAL").get(x));
                       }



                       System.out.println("TOTAL_COLLECTION " + "AIRPORT " + map.get("AIRPORT") + " " + map1.get("AIRPORT"));
                       System.out.println("PASSENGER_TYPE_SUMMARY");
                       list = new ArrayList<>();
                     a = hashMap2.get("AIRPORT").get("ADULT");
                     b = hashMap2.get("AIRPORT").get("KID");
                     c = hashMap2.get("AIRPORT").get("SENIOR_CITIZEN");
                    if(a >= b) {
                        if(a >= c) {
                            list.add("ADULT");
                            if(b >= c) {
                                list.add("KID");
                                list.add("SENIOR_CITIZEN");
                            }
                            else {
                                list.add("SENIOR_CITIZEN");
                                list.add("KID");
                            }
                        }
                        else {
                            list.add("SENIOR_CITIZEN");
                            list.add("ADULT");
                            list.add("KID");
                        }
                    }
                    else if(b >= c) {
                        list.add("KID");
                        if(a >= c) {
                            list.add("ADULT");
                            list.add("SENIOR_CITIZEN");
                        }
                        else {
                            list.add("SENIOR_CITIZEN");
                            list.add("ADULT");
                        }
                    }
                    else {
                        list.add("SENIOR_CITIZEN");
                        list.add("KID");
                        list.add("ADULT");
                    }
                    for(String x : list) {
                        if(hashMap2.get("AIRPORT").get(x) != 0)
                        System.out.println(x + " " + hashMap2.get("AIRPORT").get(x));
                    }

            }

            }
            sc.close(); // closes the scanner

        } catch (IOException e) {
        }

    }
}
/*
Input Commands & Format
BALANCE <METROCARD_NUMBER> <BALANCE_IN_THE_METROCARD>

 <METROCARD_NUMBER> is the identifier for a given MetroCard.
 <BALANCE_IN_THE_METROCARD> is the amount of money available in the MetroCard for journeys.

CHECK_IN <METROCARD_NUMBER>  <PASSENGER_TYPE> <FROM_STATION>












BALANCE  MC1 600
BALANCE  MC2 500
BALANCE MC3 50
BALANCE MC4 50
BALANCE MC5 200
CHECK_IN MC1 ADULT CENTRAL
CHECK_IN MC2 SENIOR_CITIZEN CENTRAL
CHECK_IN MC1 ADULT AIRPORT
CHECK_IN MC3 KID AIRPORT
CHECK_IN MC4 ADULT AIRPORT
CHECK_IN MC5 KID AIRPORT
PRINT_SUMMARY
 */

/*
class Test {

Upgrad upgrad;
@BeforeAll
public void init() {
     upgrad = new Upgrad();
}

@Test
public void testPrime() {
bool result = upgrad.prime(2);
assertTrue(result);
result = upgrad.prime(10);
assertFalse(result);

}
}
 */