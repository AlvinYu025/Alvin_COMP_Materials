# 数据集
## `Crime`: [Link](https://catalog.data.gov/dataset/crime-data-from-2020-to-present)
### 特征解释（来自GPT），please also refer to official info
1. **DR_NO（报告编号）**：案件的唯一标识号。  
2. **Date Rptd（报告日期）**：案件被报告给警方的日期。  
3. **DATE OCC（发生日期）**：案件实际发生的日期。  
4. **TIME OCC（发生时间）**：案件发生的时间，通常为24小时制（如2130代表21:30）。  
5. **AREA（区域代码）**：发生案件的警察辖区代码。  
6. **AREA NAME（区域名称）**：案件发生的警察辖区名称。  
7. **Rpt Dist No（报告区编号）**：更小的报告区域编号。  
8. **Part 1-2（案件类别）**：  
   - `1`：重大犯罪（如谋杀、抢劫、强奸、严重袭击、偷车等）。  
   - `2`：轻罪（如轻微袭击、伪造、赌博等）。  
9. **Crm Cd（犯罪代码）**：特定犯罪类型的代码。  
10. **Crm Cd Desc（犯罪描述）**：犯罪行为的具体描述，如`VEHICLE - STOLEN`（车辆被盗）。  
11. **Mocodes（作案手法代码）**：表示犯罪的具体作案手段，多个代码用空格隔开。  
12. **Vict Age（受害人年龄）**：受害者的年龄，`0` 可能表示未知或未填写。  
13. **Vict Sex（受害人性别）**：  
    - `M`：男性  
    - `F`：女性  
    - `X`：未知或未指定  
14. **Vict Descent（受害人族裔）**：受害者的种族或族裔代码，如 `O` 代表其他（Other）。  
15. **Premis Cd（案发地点代码）**：代表案件发生地点的代码。  
16. **Premis Desc（案发地点描述）**：描述犯罪发生的具体地点，如`STREET`（街道）或`BUS STOP/LAYOVER`（公交站）。  
17. **Weapon Used Cd（使用武器代码）**：如果案件涉及武器，则此字段会包含对应武器的代码。  
18. **Weapon Desc（武器描述）**：使用武器的描述，如`FIREARM`（枪支）。如果未使用武器，则为空。  
19. **Status（案件状态代码）**：案件的当前状态，例如：  
    - `AA`（Adult Arrest）：成人被捕  
    - `IC`（Invest Cont）：调查中  
20. **Status Desc（案件状态描述）**：对 Status 字段的详细解释，如`Adult Arrest`（成人被捕）。`Invest Cont` 说明该案件尚未完全结案。
21. **Crm Cd 1-4（犯罪代码 1-4）**：一个案件可能涉及多个犯罪类型，这些字段存储最多四个相关的犯罪代码。  
22. **LOCATION（案发地点）**：具体的案发地址。  
23. **Cross Street（交叉街道）**：如果适用，显示案件发生地点附近的交叉街道。  
24. **LAT（纬度）**：案件发生地点的纬度坐标。  
25. **LON（经度）**：案件发生地点的经度坐标。、

## `Arrest`: [Link](https://catalog.data.gov/dataset/arrest-data-from-2020-to-present)
### 特征解释（来自GPT），please also refer to official info
1. **Report ID（报告编号）**：逮捕记录的唯一标识符，如 `6636966`。  
2. **Report Type（报告类型）**：表示报告的类型，如 `BOOKING`（拘留记录）。  
3. **Arrest Date（逮捕日期）**：逮捕发生的日期，如 `07/06/2023`。  
4. **Time（逮捕时间）**：逮捕发生的时间（24小时制），如 `2250`（22:50）。  
5. **Area ID（区域编号）**：逮捕发生的区域编号，如 `8`。  
6. **Area Name（区域名称）**：逮捕发生的区域名称，如 `West LA`（西洛杉矶）。  
7. **Reporting District（报告区域）**：记录逮捕事件的区域编号，如 `817`。  
8. **Age（年龄）**：被捕者的年龄，如 `46`。  
9. **Sex Code（性别代码）**：被捕者的性别，如 `M`（男性）。  
10. **Descent Code（种族代码）**：被捕者的种族，如 `B`（黑人）。  
11. **Charge Group Code（罪名分类代码）**：罪名的类别编号，如 `4`（严重攻击）。  
12. **Charge Group Description（罪名分类描述）**：描述罪名类别，如 `Aggravated Assault`（严重攻击）。  
13. **Arrest Type Code（逮捕类型代码）**：逮捕类型，如 `F`（重罪）或 `M`（轻罪）。  
14. **Charge（罪名）**：具体的法律条款，如 `245(A)(1)PC`。  
15. **Charge Description（罪名描述）**：罪名的详细描述，如 `ADW, NOT FIREARM, W/GBI`（攻击致重伤，但未使用枪支）。  
16. **Disposition Description（案件处理情况）**：案件处理结果，如 `MISDEMEANOR COMPLAINT FILED`（已提交轻罪起诉）。  
17. **Address（地址）**：逮捕发生的具体地址，如 `900 GAYLEY AV`。  
18. **Cross Street（交叉街道）**：逮捕地点附近的交叉街道，如 `VERMONT`。  
19. **LAT（纬度）**：逮捕发生的地理位置纬度，如 `34.0637`。  
20. **LON（经度）**：逮捕发生的地理位置经度，如 `-118.4482`。  
21. **Location（地理位置）**：逮捕地点的经纬度存储格式，如 `POINT (-118.4482 34.0637)`。  
22. **Booking Date（拘留日期）**：被捕者被正式拘留的日期，如 `07/07/2023`。  
23. **Booking Time（拘留时间）**：被捕者被正式拘留的时间，如 `143`（1:43 AM）。  
24. **Booking Location（拘留地点）**：被拘留的设施名称，如 `METRO - JAIL DIVISION`（地铁监狱）。  
25. **Booking Location Code（拘留地点编号）**：拘留地点的代码，如 `4273`。  


## `Education`: [Link](https://data-lahub.opendata.arcgis.com/datasets/lahub::lausd-school-site-locations/explore?location=33.975119%2C-118.230878%2C11.45)
### 特征解释（来自GPT），please also refer to official info
1. **ObjectID（对象ID）**：数据记录的唯一标识符，如 `3226`。  
2. **Category1（一级分类）**：类别的主要分类，如 `Education`（教育）。  
3. **Category2（二级分类）**：更具体的类别，如 `Elementary Schools`（小学）、`High Schools`（高中）。  
4. **Category3（三级分类）**：更精细的分类，如 `Charter Schools`（特许学校）、`Public Schools`（公立学校）。  
5. **Name（名称）**：学校的正式名称，如 `Jardin de la Infancia`、`Central Juvenile Hall`。  
6. **Label（标签）**：学校的简要标签，与名称相同，如 `Jardin de la Infancia`。  
7. **Address Line 1（地址1）**：学校的主要地址，如 `1400 S Broadway Los Angeles, CA 90015`。  
8. **Address Line 2（地址2）**：补充地址信息，部分数据为空。  
9. **City（城市）**：学校所在的城市，如 `Los Angeles`（洛杉矶）。  
10. **State（州）**：学校所在的州，如 `CA`（加利福尼亚州）。  
11. **ZIP Code（邮政编码）**：学校的邮政编码，如 `90015-2209`。  
12. **Organization（机构）**：管理该学校的组织，如 `Los Angeles County Office of Education`（洛杉矶县教育办公室）。  
13. **Source（数据来源）**：数据的提供机构，如 `California Department of Education (CDE)`（加州教育部）。  
14. **Source ID（来源ID）**：来源数据的唯一标识，如 `1.9102E+13`。  
15. **Source Date（来源日期）**：数据来源的更新时间，如 `10/24/2023`。  
16. **Latitude（纬度）**：学校的地理位置纬度，如 `34.03533485`。  
17. **Longitude（经度）**：学校的地理位置经度，如 `-118.2624242`。  
18. **Enrollment（招生人数）**：学校的注册学生人数，如 `18`，部分数据为空。  
19. **Label Class（标签分类）**：学校的分类标签，如 `Charter Schools`（特许学校）、`Public Schools`（公立学校）。  
20. **Last Update（最后更新）**：数据的最后更新时间，如 `3/10/2025`。  
21. **x（X坐标）**：学校的地理坐标X值，如 `6482162.897`。  
22. **y（Y坐标）**：学校的地理坐标Y值，如 `1835343.217`。  


## `Population`: [Link](https://data-lahub.opendata.arcgis.com/datasets/661b1bb63c8d4c13bf5383dad5ee6c9b_0/explore?location=33.797441%2C-118.298786%2C8.53)
### 特征解释（来自GPT），please also refer to official info
1. **OBJECTID（对象ID）**：数据记录的唯一标识符，如 `1`、`2`。  
2. **CT20（人口普查区编号）**：表示人口普查的区号，如 `101110`、`101122`。  
3. **FIP22（FIPS代码）**：唯一识别地理区域的代码，如 `44000`。  
4. **CITY（城市）**：城市名称，如 `Los Angeles`（洛杉矶）。  
5. **CSA（联合统计区）**：联合统计区域，如 `Tujunga`。  
6. **CT20FIP22CSA（复合地理标识符）**：结合了普查区、FIPS代码和CSA的唯一标识，如 `10111044000Tujunga`。  
7. **SPA22（服务规划区域）**：区域编号，如 `2`。  
8. **SPA_NAME（服务规划区域名称）**：如 `San Fernando`（圣费尔南多）。  
9. **HD22（健康区编号）**：健康区编号，如 `19`。  
10. **HD_NAME（健康区名称）**：健康区名称，如 `East Valley`（东谷）。  
11. **POP22_AGE_0_4 ~ POP22_AGE_85_100（各年龄段人口数量）**：表示不同年龄段的人口数量，如 `POP22_AGE_0_4`（0-4岁）、`POP22_AGE_85_100`（85-100岁）。  
12. **POP22_WHITE（白人人口数）**：如 `2904`。  
13. **POP22_BLACK（黑人或非裔人口数）**：如 `65`。  
14. **POP22_AIAN（美洲印第安人和阿拉斯加原住民人口数）**：如 `7`。  
15. **POP22_ASIAN（亚裔人口数）**：如 `434`。  
16. **POP22_HNPI（夏威夷原住民和太平洋岛民人口数）**：如 `5`。  
17. **POP22_HISPANIC（西班牙裔人口数）**：如 `1345`。  
18. **POP22_MALE（男性人口数）**：如 `2324`。  
19. **POP22_FEMALE（女性人口数）**：如 `2436`。  
20. **POV22_WHITE（白人贫困人口数）**：如 `314`。  
21. **POV22_BLACK（黑人贫困人口数）**：如 `7`。  
22. **POV22_AIAN（美洲印第安人和阿拉斯加原住民贫困人口数）**：如 `0`。  
23. **POV22_ASIAN（亚裔贫困人口数）**：如 `45`。  
24. **POV22_HNPI（夏威夷原住民和太平洋岛民贫困人口数）**：如 `0`。  
25. **POV22_HISPANIC（西班牙裔贫困人口数）**：如 `147`。  
26. **POP22_TOTAL（总人口数）**：如 `4760`。  
27. **POV22_TOTAL（总贫困人口数）**：如 `513`。  
28. **AREA_SQMil（面积，平方英里）**：如 `0.441064347`。  
29. **POP22_DENSITY（人口密度，每平方英里）**：如 `10792.07611`。  
30. **POV22_PERCENT（贫困率，百分比）**：如 `10.77731092`。  
31. **Shape__Area（地理区域面积）**：如 `12296168.28`。  
32. **Shape__Length（地理区域边界长度）**：如 `15082.85384`。  
