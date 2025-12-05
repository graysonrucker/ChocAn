package chocan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chocan.model.*;

public class ChocAnSystem {
    private HashMap<String, Member> memberMap = new HashMap<>();
    private HashMap<String, Provider> providerMap = new HashMap<>();
    private ArrayList<ServiceRecord> serviceRecordList = new ArrayList<>();
}