import Mock from "mockjs";

Mock.setup({ timeout: "1000" });
Mock.mock("/api/docSimple", "get", () => {
  return {
    data: {
      id: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      title: "淮北市城乡规划条例",
      office: "淮北市人民代表大会常务委员会",
      publish: "",
      expiry: "2017-01-01",
      status: "生效",
      type: "地方性法规",
      suffix: "条例",
      docCategory: "地方性法规",
      docText:
        "淮北市城乡规划条例（2016年9月14日淮北市第十五届人民代表大会常务委员会第三十三次会议通过  2016年11月10日安徽省第十二届人民代表大会常务委员会第三十四次会议批准）",
      articleSum: 0,
      divided: 1,
      docURL: "https://www.apple.com",
    },
  };
});

Mock.mock("/api/part", "get", () => {
  return {
    data: [],
  };
});
// 章
Mock.mock("/api/chap", "get", () => {
  return {
    data: [
      {
        id: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        chapSeq: "第一章",
        chapName: " 总  则",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "6a198395-fdab-471b-9258-212d1f852ec1",
        chapSeq: "第二章",
        chapName: " 城乡规划的制定和修改",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        chapSeq: "第三章",
        chapName: " 城乡规划的实施",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        chapSeq: "第四章",
        chapName: " 监督检查",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "48326ce2-fda2-4f90-8523-9df798089be4",
        chapSeq: "第五章",
        chapName: " 法律责任",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "c895adf6-cd6d-45b8-bd09-be290b28ba9a",
        chapSeq: "第六章",
        chapName: " 附  则",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
    ],
  };
});

Mock.mock("/api/sect", "get", () => {
  return {
    data: [],
  };
});

Mock.mock("/api/articleSimple", "get", () => {
  return {
    data: [
      {
        id: "05378ec5-4173-4c25-bc0a-9160277de0d6",
        articleSeq: "第五十五条",
        articleName:
          " 本条例第五十三条所称不能拆除的违法建设包括：@SubP@（一）拆除违法建设对公共利益造成重大损害的；@SubP@（二）拆除违法建设损害无过错利害关系人合法权益的；@SubP@（三）拆除违法建设影响建筑结构安全或者相邻建筑结构安全的。",
        sectId: null,
        chapId: "48326ce2-fda2-4f90-8523-9df798089be4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "07fddb2c-578c-4476-a3c6-2a6f67d1e9c6",
        articleSeq: "第二条",
        articleName: " 本条例适用于本市行政区域内制定和实施城乡规划，在规划区内进行建设活动及其监督管理。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "0c9e1fdb-01ec-4074-ad2b-246bec69f556",
        articleSeq: "第四十二条",
        articleName:
          " 建设工程规划核实合格后，未经城乡规划主管部门规划许可，任何单位和个人不得擅自改建、扩建或者改变建筑物、构筑物和其他设施使用功能。确需改建、扩建或者改变使用功能的，应当按照本条例第三十三条的规定重新履行规划许可程序。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "134adf61-61d7-4965-bb62-8bd2c3be3f15",
        articleSeq: "第四十六条",
        articleName:
          " 市、县人民政府及其城乡规划主管部门应当将城乡规划编制和修改以及容积率、用地性质等规划条件变更，纳入重大事项集体决策范围，实行决策事项终身负责制。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "18c50dfe-56ed-4dfa-86f9-c96fa9e3745f",
        articleSeq: "第二十一条",
        articleName:
          " 经依法批准的城乡规划的修改，应当符合《中华人民共和国城乡规划法》《安徽省城乡规划条例》和本条例的规定，并按照原程序报批、备案。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "2293ce19-273d-489f-88ff-ea5e32aa9992",
        articleSeq: "第八条",
        articleName:
          " 城乡规划的制定、修改、实施和监督检查，应当建立健全公众参与制度，听取公众意见。@Para@任何单位和个人有权对城乡规划的制定、修改和实施提出意见和建议，有权就涉及其利害关系的建设活动是否符合城乡规划向城乡规划主管部门进行查询，有权对违反城乡规划的行为进行举报和控告。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "23bcb186-8e8b-42be-92f4-09eab02efcc0",
        articleSeq: "第三十二条",
        articleName:
          " 以出让方式取得国有土地使用权的建设项目，在签订国有土地使用权出让合同后，建设单位应当持建设项目的批准、核准、备案文件和国有土地使用权出让合同，向城乡规划主管部门领取建设用地规划许可证。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "266cae14-cc30-4c15-a1f9-11104b164c3b",
        articleSeq: "第三十六条",
        articleName:
          " 经审定的修建性详细规划、建设工程设计方案不得擅自修改。符合下列情形之一的，按照规定的程序修改：@SubP@（一）因控制性详细规划的修改导致无法按照修建性详细规划、建设工程设计方案建设的；@SubP@（二）因文物保护、地质灾害和其他涉及公共利益原因致使无法按照修建性详细规划、建设工程设计方案建设的；@SubP@（三）整体建设项目或者分期建设项目内的独立地块，在尚未实施建设、不违背规划条件的前提下，建设单位申请修改的；@SubP@（四）法律、法规规定的其他情形。@Para@修改修建性详细规划、建设工程设计方案，应当召开听证会听取利害关系人的意见。城乡规划主管部门应当将修改草案予以公示，公示时间不得少于十日。因修改给利害关系人合法权益造成损失的，建设单位应当依法给予补偿。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "2737515a-1683-4e31-8e7c-ec4d00bb45c8",
        articleSeq: "第五十一条",
        articleName:
          " 市、县人民政府城乡规划主管部门和相关行业主管部门，应当将规划编制和工程建设中涉及的建设、设计、测绘、施工、监理等单位及个人的违法违规行为纳入征信系统，作为信用信息予以记录并公布。@Para@对涉及违法建设的设计、施工、监理等单位及其直接责任人员，市、县人民政府城乡规划主管部门、城市管理综合执法部门应当及时通报市、县人民政府建设行政主管部门处理。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "3018473c-cf67-4cab-af59-00e303b0b86d",
        articleSeq: "第二十七条",
        articleName:
          " 城市规划建设应当将建筑与居住区雨水收集利用、可渗透面积、蓝线划定与保护等海绵城市建设要求作为城市规划设计建设的重要内容，保持雨水径流特征在城市开发建设前后大体一致。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "3222584c-131b-4f3f-8d17-d4a12c8b3f66",
        articleSeq: "第二十三条",
        articleName:
          " 城市新区建设应当坚持空间规划与区域产业规划相协调的原则，突出区域特色，合理安排各类规划建设用地，合理确定建设规模和时序，充分利用现有市政基础设施和公共服务设施建设，严格保护自然资源和生态环境，塑造富有地方特色的城市空间形态。@Para@在城市总体规划、镇总体规划确定的建设用地范围以外，不得设立各类开发区和城市新区。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "33dd5e85-ba88-4902-b85e-a321c360a806",
        articleSeq: "第四条",
        articleName: " 依法批准的城乡规划是城乡建设和规划管理的依据，未经法定程序不得修改。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "368cd51a-0132-4637-b365-098d17d6fdbb",
        articleSeq: "第三十一条",
        articleName:
          " 在规划区内以出让方式提供国有土地使用权的，在出让前城乡规划主管部门应当依据控制性详细规划提出规划条件，作为国有土地使用权出让合同的组成部分。未确定规划条件的地块，不得出让国有土地使用权。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "36e95b7c-7649-47ef-95e0-2cb7d3035ead",
        articleSeq: "第十二条",
        articleName:
          " 市、县有关行业主管部门应当根据城市、镇总体规划组织编制各类专业规划，征求本级人民政府城乡规划主管部门意见后，报本级人民政府审批。法律、法规另有规定的，从其规定。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "408a6cb8-91c4-4eee-b29d-780548884d3b",
        articleSeq: "第二十二条",
        articleName:
          " 市、县和镇人民政府应当在编制国民经济和社会发展规划时，同步编制城市、镇的近期建设规划，有计划、分步骤地组织实施城乡规划，并报总体规划审批机关备案。@Para@近期建设规划应当以重要基础设施、公共服务设施和中低收入居民住房建设以及生态环境保护为重点内容，并根据当地经济社会发展水平合理确定建设规模和时序。@Para@市、县人民政府相关部门制定旧城改造、土地收储供应、保障性住房建设、生态环境建设、城建投资等年度实施计划，应当依据近期建设规划。城乡规划主管部门应当参与年度实施计划的制定。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "4d12176c-b846-430b-a963-2da28dfc4521",
        articleSeq: "第十五条",
        articleName:
          " 市、县人民政府城乡规划主管部门应当组织编制城市设计，对城市和县人民政府所在地镇的重要区域、主要轴线和关键节点的建筑、公共空间的形态、布局和景观控制提出规划管理的要求，统筹城市建设布局，协调城市景观风貌，体现地域特征。@Para@城市设计应当遵守城市总体规划和控制性详细规划的强制性内容。城市设计内容应当纳入控制性详细规划或者作为控制性详细规划相关内容的补充。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "4eb50ad8-8846-44a7-bff8-14ff383c91e6",
        articleSeq: "第三十五条",
        articleName:
          " 建设工程开工前，建设单位或者个人应当委托具有相应测绘资质的单位依据建设工程规划许可证进行放线。建设工程基础、管线等隐蔽工程完工后，建设单位应当向建设工程规划许可证核发机关申请组织验线。经验线合格的方可继续施工，验线不合格的，责令建设单位立即停止施工并改正。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "5869f47a-2259-4270-b2a0-291cde2bf283",
        articleSeq: "第四十条",
        articleName:
          " 在村庄规划区内进行农村村民住宅建设的，申请人应当持村民委员会证明材料、户口簿及其复印件，向镇人民政府提出申请。@Para@使用原有宅基地和其他非农用地的，镇人民政府应当自收到申请之日起十日内作出决定，符合村庄规划的，核发乡村建设规划许可证；不符合村庄规划的，不予核发乡村建设规划许可证，书面告知申请人，并说明理由。@Para@确需占用农用地的，申请人还应当提供农用地转用批准材料。镇人民政府应当自收到申请之日起十日内提出审查意见，报市、县人民政府城乡规划主管部门。城乡规划主管部门应当自收到审查意见之日起十五日内做出决定，符合村庄规划的，核发乡村建设规划许可证；不符合村庄规划的，不予核发乡村建设规划许可证，书面告知申请人，并说明理由。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "5e13057b-0c52-4979-a44e-c183eb001552",
        articleSeq: "第二十六条",
        articleName:
          " 城市地下空间的开发和利用，应当符合地下空间开发利用规划，在保证公共安全的前提下，优先满足防灾减灾、人民防空、地下管网等基础设施的需要。@Para@新建、改建、扩建城市道路工程应当根据功能需求同步规划建设地下综合管廊，因特殊原因不能同步的，应当进行管线综合规划设计，统筹各类管线建设。已建成地下综合管廊的道路，不得另行擅自开挖铺设管线。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "63857e87-6b58-4118-a8dd-c459f2fb9a44",
        articleSeq: "第四十九条",
        articleName:
          " 任何单位和个人向城乡规划主管部门、城市管理综合执法部门或者其他有关部门举报或者控告违反城乡规划行为的，受理部门接到举报或者控告后十五日内应当作出处理，并反馈举报人或者控告人；不属于本部门职责范围的，应当及时移送有关部门处理，并将移送情况反馈举报人或者控告人。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "66d5ab56-451e-4dcc-84d1-8d30794da7a9",
        articleSeq: "第二十五条",
        articleName:
          " 矿产资源的开采应当符合城乡规划。@Para@矿产资源开采企业应当按照以人为本、保护生态、先征后用、先搬后采的原则实施资源开采，并依据城乡规划对采矿区以及塌陷区进行综合治理和生态修复。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "69a1e97f-85dd-4390-9b9c-2e09de78a8f8",
        articleSeq: "第九条",
        articleName:
          " 城乡规划包括城市规划、镇规划和村庄规划。城市规划、镇规划分为总体规划和详细规划。详细规划分为控制性详细规划和修建性详细规划。@Para@城乡规划的编制应当依法确定规划的强制性内容。编制下一层次规划不得违背和变更上一层次规划确定的强制性内容，并应当对上一层次规划确定的强制性内容作出具体安排。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "6b30f986-3eed-4c7f-8fbc-47ad68d6e02f",
        articleSeq: "第十七条",
        articleName:
          " 村庄规划应当从农村实际出发，保护耕地、节约用地，方便群众生活、改善农村环境，尊重村民意愿，体现地方特色，优先安排必需的基础设施和公共服务设施建设。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "6c30c75c-c2c8-4686-82b4-fe331fa1b2f6",
        articleSeq: "第三十条",
        articleName:
          " 在城市、镇规划区内以划拨方式取得国有土地使用权的建设项目，建设单位应当持建设项目批准、核准或者备案文件向城乡规划主管部门提出建设用地规划许可申请。城乡规划主管部门依据控制性详细规划和相关规划，核定建设用地的位置、面积、允许建设的范围，应当自收到申请之日起三十日内核发建设用地规划许可证。@Para@建设单位在取得建设用地规划许可证后，方可申请用地。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "6ff6162f-26bb-4ec0-bd04-3d9cbbd9e814",
        articleSeq: "第三十三条",
        articleName:
          " 在城市、镇规划区内进行建筑物、构筑物、道路、管线和其他工程建设的，建设单位或者个人应当持使用土地的有关证明文件、建设工程设计方案等材料，向城乡规划主管部门或者省人民政府确定的镇人民政府申请办理建设工程规划许可证。@Para@城乡规划主管部门或者省人民政府确定的镇人民政府对修建性详细规划、建设工程设计方案进行审核，符合规划条件的，应当自收到申请之日起三十日内核发建设工程规划许可证；对不符合要求的，不予核发建设工程规划许可证，书面告知申请人，并说明理由。@Para@建设项目包含地下工程的，地下工程应当与地上工程同时办理建设工程规划许可证。@Para@规划要求配置绿地、幼儿园、中小学校、停车场、菜市场、公共交通站场、消防设施、环卫设施、物业管理用房、社区办公服务用房、社区养老等配套设施的城镇建设项目，配套设施应当与主体工程统一设计、同步建设、同时交付使用。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "7109f9bc-2311-4c7f-9100-da29ac98151e",
        articleSeq: "第五十三条",
        articleName:
          " 对未取得建设工程规划许可证或者未按照建设工程规划许可证的规定进行建设的，由城市管理综合执法部门按照下列规定予以处理：@SubP@（一）责令停止建设，拒不停止建设的，依法查封施工现场；@SubP@（二）尚可采取改正措施消除对规划实施影响的，责令限期改正，对按期改正的处建设工程造价百分之五的罚款；对逾期不改正的，依法强制拆除，并处建设工程造价百分之五以上百分之十以下的罚款；@SubP@（三）无法采取改正措施消除对规划实施影响的，责令限期拆除，不能拆除的，没收实物或者违法收入，可以并处建设工程造价百分之十以下的罚款。",
        sectId: null,
        chapId: "48326ce2-fda2-4f90-8523-9df798089be4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "75d68773-b45c-4fdf-a745-e7775f4c7ede",
        articleSeq: "第一条",
        articleName:
          " 为了制定和实施城乡规划，协调城乡空间布局，改善人居环境，促进城乡经济社会和生态环境全面协调可持续发展，根据《中华人民共和国城乡规划法》和有关法律、行政法规，结合本市实际，制定本条例。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "763e90e9-a7aa-4fd5-9a09-ffd8482d2142",
        articleSeq: "第十六条",
        articleName:
          " 经国务院或者省人民政府批准设立（筹建）的各类开发区的总体规划由主办开发区的人民政府组织编制，报省人民政府审批。@Para@开发区控制性详细规划由开发区管委会组织编制，经市、县人民政府城乡规划主管部门审查后，报本级人民政府审批。批准后的开发区控制性详细规划，报本级人民代表大会常务委员会和上一级人民政府备案。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "76ab0836-e6e7-43e9-91f3-edd1b35a8986",
        articleSeq: "第四十七条",
        articleName:
          " 市、县人民政府及其城乡规划主管部门、城市管理综合执法部门应当按照各自职责依法加强对城乡规划的监督检查，依法查处违法违规编制、审批、实施、修改城乡规划的行为。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "782424e9-907d-4ef5-886b-e859dba96192",
        articleSeq: "第三十八条",
        articleName:
          " 建设单位或者个人因工程建设需要可以在用地红线范围内修建临时工棚、围墙或者其他临时建（构）筑物。@Para@临时建（构）筑物应当设置明显标识，使用期限不得超过二年。临时建筑物不得超过两层，总高度不得超过七米，不得开发地下空间。@Para@因特殊情况需在局部地段临时修建城市市政、公用设施的，应当向城乡规划主管部门申请办理临时建设工程规划许可证。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "792f1143-cb16-4892-8b2c-9352067c4a1f",
        articleSeq: "第四十三条",
        articleName:
          " 建设单位或者个人申请施工用水、用电时，供水、供电单位应当要求申请人出示建设工程规划许可证、乡村建设规划许可证或者临时建设工程规划许可证。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "7a533018-f800-45f8-83b9-561e4170adf7",
        articleSeq: "第四十五条",
        articleName:
          " 市人民政府应当建立城乡规划督察员制度，向县人民政府派驻城乡规划督察员。@Para@城乡规划督察员对城乡规划违法行为，应当及时向县人民政府及其城乡规划主管部门提出督察意见，同时将督察意见报市人民政府城乡规划主管部门。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "7ac06eca-6400-4fa9-835f-8a288dafd3ef",
        articleSeq: "第五十条",
        articleName:
          " 各级人民政府及其有关部门应当建立健全工程建设领域信息共享、监管联动机制和查处违法建设信息抄告反馈制度，并按照下列规定明确职责分工：@SubP@（一）市人民政府城乡规划主管部门负责自核发建设工程规划许可证至建设工程规划核实前的建设工程的批后监管，发现违法建设，移交城市管理综合执法部门依法查处；@SubP@（二）市人民政府城市管理综合执法部门负责跨区域的重大违法建设的查处，并负责指导、监督、考核县（区）违法建设的查处工作；@SubP@（三）市人民政府城乡建设、国土资源、交通运输、水务、林业等部门按照各自职责，依法做好违法建设查处工作；工商、卫生、文化、环保等部门在核发有关许可证和执照时，应当对经营场所的合法性严格审核把关；@SubP@（四）县（区）人民政府负责辖区内违法建设的查处工作，对违法建设行为依法组织实施行政强制；@SubP@（五）镇人民政府负责查处本辖区内未取得乡村建设规划许可证或者未按照乡村建设规划许可证的规定进行建设的违法行为；@SubP@（六）街道办事处、居民委员会、物业服务企业应当对日常巡查中发现的违法建设行为予以劝阻，并及时报告城市管理综合执法部门。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "802b8aa0-cfdb-4996-aa01-1e7442446683",
        articleSeq: "第二十九条",
        articleName:
          " 按照国家规定需由有关部门批准或者核准的建设项目，以划拨方式取得国有土地使用权的，建设单位在报送有关部门批准、核准前，应当持相关文件向城乡规划主管部门申请核发选址意见书。对符合规划要求的，城乡规划主管部门应当自收到申请之日起三十日内核发选址意见书；不符合城乡规划的，不予核发选址意见书，书面告知申请人，并说明理由。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "8402c2ce-6f64-42c6-880d-114754005cea",
        articleSeq: "第五条",
        articleName:
          " 市、县人民政府设立城乡规划委员会。城乡规划委员会对本行政区域内城乡规划制定、实施中的重大事项提出审查意见，作为本级人民政府规划决策的依据。@Para@城乡规划委员会由本级人民政府及其相关职能部门代表、专家和公众代表组成，其中专家和公众代表比例不低于四分之一。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "84c949a4-ddbf-44f8-8682-23e1fa3c7d94",
        articleSeq: "第七条",
        articleName:
          " 市、县人民政府城乡规划主管部门应当建立城乡规划信息公开平台，及时公布依法批准的城乡规划及相关城乡规划信息。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "8ea0d651-bee3-4d56-827f-1a9f5e3f3a47",
        articleSeq: "第三十四条",
        articleName:
          " 建设工程实行批前公示和批后公告制度。@Para@城乡规划主管部门或者省人民政府确定的镇人民政府在核发建设工程规划许可证前，应当对拟批准的内容进行批前公示，公示期不得少于七日。@Para@建设单位或者个人在取得建设工程规划许可证后，应当在施工现场设立建设工程规划许可公告牌，对外公示建设工程规划许可证及建设工程设计方案总平面图等内容，并在建设工程竣工验收前保持公告牌及其内容的完整。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "9914868e-9115-482c-9ad0-15976c41055e",
        articleSeq: "第六条",
        articleName:
          " 市人民政府城乡规划主管部门负责本市城乡规划管理工作，并根据工作需要设立派出机构，负责指定区域城乡规划管理的具体工作。@Para@县人民政府城乡规划主管部门负责本辖区内城乡规划管理工作，业务上接受市人民政府城乡规划主管部门的指导和监督。@Para@市、县（区）人民政府的有关部门和镇人民政府、街道办事处应当按照各自职责，协同做好城乡规划管理的相关工作。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a64c48e5-c122-4872-80e7-d3d4b13abf6a",
        articleSeq: "第二十四条",
        articleName:
          " 市、县人民政府应当有计划地组织实施旧城区的更新改造，科学确定更新改造的控制性规划建设指标。@Para@旧城区改造应当以街区为单元实施整体改造，并与区域内基础设施和公共服务设施承载力相适应；合理确定开发强度，严格控制新建住宅项目，降低人口密度，增加绿地、广场、停车场等公共空间，保护历史建筑和历史文化街区，延续传统风貌，改善人居环境和城市面貌。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "ad2f520b-1b85-4d6b-b4a7-e7c54b6517b9",
        articleSeq: "第二十八条",
        articleName:
          " 建设项目的规划管理实行建设项目选址意见书、建设用地规划许可证、建设工程规划许可证、乡村建设规划许可证、建设工程规划核实等制度。规划许可证件生效期为一年，需要延期的，应当在期限届满三十日前向原审批机关申请，经批准延期的，其延长期限不得超过六个月。@Para@取得选址意见书之日起一年内未取得建设用地规划许可证或者未取得有关部门批准、核准文件，且未经批准延期的，选址意见书失效。@Para@取得建设用地规划许可证之日起一年内未取得国有土地使用证，且未经批准延期的，建设用地规划许可证失效。@Para@取得建设工程规划许可证或者乡村建设规划许可证之日起一年内未进行建设，且未经批准延期的，建设工程规划许可证或者乡村建设规划许可证失效。@Para@其他规划文书的时效由审批机关在核发该文书时确定。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "b281a28a-12c3-48ec-8cb9-1b70c7e0b958",
        articleSeq: "第十条",
        articleName:
          " 城市总体规划由市人民政府组织编制，经省人民政府审查同意后，报国务院审批。@Para@市辖区内镇的总体规划由所在地镇人民政府组织编制，位于城市规划区内的报市人民政府审批，位于城市规划区外的报所在地的区人民政府审批。@Para@县人民政府所在地镇的总体规划，由县人民政府组织编制，报市人民政府审批。县域内其他镇的总体规划由镇人民政府组织编制，报县人民政府审批。@Para@镇人民政府应当组织编制村庄规划, 报所在地县（区）人民政府审批。@Para@纳入城市、镇规划建设用地范围内的村庄不再单独编制村庄规划。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "b5c522d6-908b-4387-9135-f30cc3b8d954",
        articleSeq: "第四十四条",
        articleName:
          " 各级人民政府应当定期对城市、镇、经济开发区（工业园区）的总体规划和控制性详细规划实施情况进行评估，并将实施情况向本级人民代表大会常务委员会或者镇人民代表大会报告，接受监督。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "b942466b-cf66-49bc-a4af-3c027dc8cc67",
        articleSeq: "第五十二条",
        articleName:
          " 各级人民政府、城乡规划主管部门和其他有关部门及其工作人员有下列行为之一的，由有关部门责令改正，通报批评；对直接负责的主管人员和其他直接责任人员依法给予处分：@SubP@（一）依法应当编制城乡规划而未组织编制，或者未按照法定程序编制、审批、修改城乡规划的；@SubP@（二）超越职权或者违法作出许可以及应当许可而不予许可的；@SubP@（三）未依法在国有土地使用权出让合同中确定规划条件，或者擅自改变国有土地使用权出让合同中依法确定的规划条件的；@SubP@（四）对违法建设行为不依法查处以及不履行配合查处职责的；@SubP@（五）未依法实施行政强制，致使违法建设扩大的；@SubP@（六）违反规定对违法建设降低标准进行处罚，或者对应当依法拆除的违法建设不予拆除的；@SubP@（七）对未取得规划核实证明或者擅自改变规划许可用途的建设工程办理竣工验收备案手续的；@SubP@（八）法律、法规规定的其他情形。",
        sectId: null,
        chapId: "48326ce2-fda2-4f90-8523-9df798089be4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "bb3b6a1b-8c8f-4065-883c-65bb8c0ebf94",
        articleSeq: "第五十六条",
        articleName: " 本条例自2017年1月1日起施行。",
        sectId: null,
        chapId: "c895adf6-cd6d-45b8-bd09-be290b28ba9a",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "bd670265-26b0-4719-8267-150c53ea5470",
        articleSeq: "第三十七条",
        articleName:
          " 在建设工程规划核实前，有下列情形之一，确需对已取得的建设工程规划许可证进行变更的，建设单位或者个人应当向发证机关申请办理变更手续：@SubP@（一）建设主体名称已依法变更的；@SubP@（二）因公共利益需要增加公共配套设施和交通、市政设施的；@SubP@（三）因消防、地质、建筑结构等安全需要的；@SubP@（四）根据本条例第三十六条修改修建性详细规划或者建设工程设计方案，致使建设工程规划许可内容发生改变的；@SubP@（五）违法建设行为导致建设工程规划许可内容发生改变，经行政处罚后确需变更的；@SubP@（六）法律、法规规定的其他情形。@Para@变更内容涉及相关部门审查内容的，受理申请的部门应当征求相关部门的意见。@Para@申请变更的内容涉及利害关系人利益的，受理申请的部门应当采取公示、听证等方式听取利害关系人的意见。@Para@变更后的建设工程规划许可证内容及附图应当按照规定予以公布。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "c22a046a-17b4-40ea-af18-928608737405",
        articleSeq: "第三条",
        articleName:
          " 制定和实施城乡规划，应当突出山、水、城、林交融一体的城市特色，遵循以人为本、城乡统筹、因地制宜、合理布局、节约用地、集约发展的原则，坚持先规划后建设、无规划不建设，注重对城市空间立体性、平面协调性、风貌整体性、文脉传承性等方面的规划管理，增强规划的前瞻性、科学性、严肃性、规范性、完整性、连续性。",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "c8bafb48-21c9-42e5-ba82-ece0502f3b1d",
        articleSeq: "第四十一条",
        articleName:
          " 建设工程竣工后，建设单位或者个人应当持建设工程规划许可证、建设工程竣工核实技术报告，向城乡规划主管部门申请办理规划核实手续。@Para@城乡规划主管部门应当自收到申请之日起三十日内予以核实；对不符合规划条件和规划许可要求的，发放不予通过的通知并载明不予通过的理由。@Para@未经核实或者经核实不符合规划条件和规划许可要求的，建设单位不得组织竣工验收。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "d28d5030-6e4d-4328-a84b-e4e1c8d8fa2a",
        articleSeq: "第三十九条",
        articleName:
          " 在村庄规划区内进行乡镇企业、乡村公共设施和公益事业建设的单位或者个人，应当在办理用地审批手续前，持村民委员会书面同意意见和项目批准、核准或者备案文件以及建设工程设计方案，向镇人民政府提出申请；确需占用农用地的，还应当提供农用地转用批准材料。@Para@镇人民政府应当自收到申请之日起十日内提出审查意见，报市、县人民政府城乡规划主管部门。城乡规划主管部门应当自收到审查意见之日起十五日内作出决定，符合村庄规划的，核发乡村建设规划许可证；不符合村庄规划的，不予核发乡村建设规划许可证，书面告知申请人，并说明理由。",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "d3706c27-b079-4103-b47c-141370a4fbb5",
        articleSeq: "第十八条",
        articleName:
          " 市人民政府应当组织编制相山、龙脊山、南湖、中湖、东湖等风景区和湿地的保护规划，并报市人民代表大会常务委员会备案。风景区和湿地保护规划确需修改的，应当由市人民政府组织听证，报市人民代表大会常务委员会审议。@Para@风景区的核心区内禁止建设与风景区保护无关的建筑物。风景区周边的建设，应当注重保护自然山体、河湖湿地、水体轮廓线，严格控制建筑密度和建筑高度。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "daabda9e-4a3f-4c9f-b253-37e67f0cb2bb",
        articleSeq: "第十九条",
        articleName:
          " 市人民政府应当组织编制濉河、岱河、龙河、龙岱河、闸河等城市水系（水体）及周边景观的整体规划，划定河道管理范围和保护范围，严格控制河流周边的开发建设。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "e2221eb2-ebda-4eff-aa81-833b4c0c1a18",
        articleSeq: "第十一条",
        articleName:
          " 市、县人民政府组织编制的总体规划在报送审批前，应当先经本级人民代表大会常务委员会审议。市、县人民政府应当对常务委员会组成人员的审议意见进行研究处理并提出书面报告。@Para@镇人民政府组织编制的镇总体规划在报送审批前，应当先经镇人民代表大会审议。镇人民政府应当对代表的审议意见进行研究处理，并反馈处理情况。@Para@村庄规划在报送审批前，应当经村民会议或者村民代表会议讨论同意。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "eb7d3a47-4d1d-4f9f-a493-d059933be8c3",
        articleSeq: "第四十八条",
        articleName:
          " 市、县人民政府城乡规划主管部门应当在规划信息公开平台将以下城乡规划信息，自形成或者批准之日起十五日内向社会公开：@SubP@（一）依法制定和修改的城乡规划;@SubP@（二）规划许可的条件、程序和作出的许可决定;@SubP@（三）城乡规划实施的监督检查情况以及处理结果;@SubP@（四）其他依照法律、法规和国家有关规定应当公开的信息。@Para@市、县人民政府城乡规划主管部门应当设立规划展示固定场所，并配备方便查询的设施、设备。",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "efa86ce2-2364-4a7e-a0a0-2bd16b90e269",
        articleSeq: "第十三条",
        articleName:
          " 城市总体规划、镇总体规划确定的建设用地范围，应当编制控制性详细规划，未编制控制性详细规划的建设用地不得进行建设。@Para@主城区的控制性详细规划，由市人民政府城乡规划主管部门组织编制，报市人民政府审批。@Para@县人民政府所在地镇的控制性详细规划，由县人民政府城乡规划主管部门组织编制，报县人民政府审批；城市规划区内镇的控制性详细规划，由镇人民政府组织编制，报市人民政府审批；其他镇的控制性详细规划，由镇人民政府组织编制，报县（区）人民政府审批。@Para@经批准的控制性详细规划，应当报批准机关的本级人民代表大会常务委员会和上一级人民政府备案。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "fadea029-2fdf-438a-9bc9-69baee788649",
        articleSeq: "第五十四条",
        articleName:
          " 未经批准进行临时建设、未按照批准内容进行临时建设或者临时建（构）筑物超过批准期限不拆除的，由城市管理综合执法部门责令限期拆除，可以并处临时建设工程造价一倍的罚款。",
        sectId: null,
        chapId: "48326ce2-fda2-4f90-8523-9df798089be4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "fbf6936b-7b38-49c7-be61-de758fd5a4ab",
        articleSeq: "第二十条",
        articleName:
          " 市、县人民政府应当组织编制各级重点文物保护单位保护规划以及重要历史建筑、工业遗存、古树名木保护规划，编制柳孜运河遗址、临涣历史文化名镇、濉溪老城石板街区等特定区域的保护规划，科学划定建设控制地带和保护范围。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "fea93ecf-c5ee-4a74-bfcb-7f4b4fd7463d",
        articleSeq: "第十四条",
        articleName:
          " 控制性详细规划包括下列强制性内容：@SubP@（一）土地使用性质及其兼容性等用地功能控制要求；@SubP@（二）容积率、建筑高度、建筑密度、绿地率等指标；@SubP@（三）基础设施、公共服务设施、公共安全设施的用地规模、范围及具体控制要求，地下空间及人防工程综合利用的规模，地下管线控制要求；@SubP@（四）城市道路用地控制界线、基础设施用地控制界线、各类绿地范围控制界线、历史文化街区和历史建筑保护范围界线、地表水体保护和控制地域界线及控制要求；@SubP@（五）其他需要纳入强制性内容的。",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
    ],
  };
});

Mock.mock("/api/paraSimple", "get", () => {
  return {
    data: [
      {
        id: "01cee91e-5ba2-4428-87a9-e3eae7c97d73",
        paraSeq: "第1款",
        paraName:
          "取得选址意见书之日起一年内未取得建设用地规划许可证或者未取得有关部门批准、核准文件，且未经批准延期的，选址意见书失效。",
        articleId: "ad2f520b-1b85-4d6b-b4a7-e7c54b6517b9",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "03d6b71d-36c7-4d19-ad07-52392b26da4c",
        paraSeq: "第2款",
        paraName: "取得建设用地规划许可证之日起一年内未取得国有土地使用证，且未经批准延期的，建设用地规划许可证失效。",
        articleId: "ad2f520b-1b85-4d6b-b4a7-e7c54b6517b9",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "04281989-c2dc-447c-a4b0-d09d93b8ba92",
        paraSeq: "第2款",
        paraName: "村庄规划在报送审批前，应当经村民会议或者村民代表会议讨论同意。",
        articleId: "e2221eb2-ebda-4eff-aa81-833b4c0c1a18",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "0916de51-d710-4ffd-a044-6c8f27de79f6",
        paraSeq: "第3款",
        paraName: "经批准的控制性详细规划，应当报批准机关的本级人民代表大会常务委员会和上一级人民政府备案。",
        articleId: "efa86ce2-2364-4a7e-a0a0-2bd16b90e269",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "0b5354bf-30ca-4595-968e-2b7d126f711e",
        paraSeq: "第1款",
        paraName:
          "修改修建性详细规划、建设工程设计方案，应当召开听证会听取利害关系人的意见。城乡规划主管部门应当将修改草案予以公示，公示时间不得少于十日。因修改给利害关系人合法权益造成损失的，建设单位应当依法给予补偿。",
        articleId: "266cae14-cc30-4c15-a1f9-11104b164c3b",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "0cf1bbad-ca53-4c9a-be81-2f587b480ac8",
        paraSeq: "第1款",
        paraName:
          "城市设计应当遵守城市总体规划和控制性详细规划的强制性内容。城市设计内容应当纳入控制性详细规划或者作为控制性详细规划相关内容的补充。",
        articleId: "4d12176c-b846-430b-a963-2da28dfc4521",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "0e340977-d212-4363-acce-c6d0018641b2",
        paraSeq: "第2款",
        paraName: "申请变更的内容涉及利害关系人利益的，受理申请的部门应当采取公示、听证等方式听取利害关系人的意见。",
        articleId: "bd670265-26b0-4719-8267-150c53ea5470",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "17ca5a1d-17a5-4808-8524-ff845acbdb6b",
        paraSeq: "第1款",
        paraName:
          "镇人民政府应当自收到申请之日起十日内提出审查意见，报市、县人民政府城乡规划主管部门。城乡规划主管部门应当自收到审查意见之日起十五日内作出决定，符合村庄规划的，核发乡村建设规划许可证；不符合村庄规划的，不予核发乡村建设规划许可证，书面告知申请人，并说明理由。",
        articleId: "d28d5030-6e4d-4328-a84b-e4e1c8d8fa2a",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "21e79fd0-8be5-43ad-bec8-49cfaf07e866",
        paraSeq: "第1款",
        paraName:
          "使用原有宅基地和其他非农用地的，镇人民政府应当自收到申请之日起十日内作出决定，符合村庄规划的，核发乡村建设规划许可证；不符合村庄规划的，不予核发乡村建设规划许可证，书面告知申请人，并说明理由。",
        articleId: "5869f47a-2259-4270-b2a0-291cde2bf283",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "233610b2-5195-4070-a8d9-a2c28adba615",
        paraSeq: "第2款",
        paraName:
          "建设单位或者个人在取得建设工程规划许可证后，应当在施工现场设立建设工程规划许可公告牌，对外公示建设工程规划许可证及建设工程设计方案总平面图等内容，并在建设工程竣工验收前保持公告牌及其内容的完整。",
        articleId: "8ea0d651-bee3-4d56-827f-1a9f5e3f3a47",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "356a9daf-8735-4426-af29-40d0c672bc07",
        paraSeq: "第1款",
        paraName:
          "开发区控制性详细规划由开发区管委会组织编制，经市、县人民政府城乡规划主管部门审查后，报本级人民政府审批。批准后的开发区控制性详细规划，报本级人民代表大会常务委员会和上一级人民政府备案。",
        articleId: "763e90e9-a7aa-4fd5-9a09-ffd8482d2142",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "36ad3a22-61a3-47d0-80c5-452686f8f562",
        paraSeq: "第3款",
        paraName:
          "取得建设工程规划许可证或者乡村建设规划许可证之日起一年内未进行建设，且未经批准延期的，建设工程规划许可证或者乡村建设规划许可证失效。",
        articleId: "ad2f520b-1b85-4d6b-b4a7-e7c54b6517b9",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "386d5e83-41dc-4312-aa50-5d22c1d3e29a",
        paraSeq: "第1款",
        paraName:
          "城乡规划的编制应当依法确定规划的强制性内容。编制下一层次规划不得违背和变更上一层次规划确定的强制性内容，并应当对上一层次规划确定的强制性内容作出具体安排。",
        articleId: "69a1e97f-85dd-4390-9b9c-2e09de78a8f8",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "3b1b3eb3-b42a-4ea6-8614-37b32f40150d",
        paraSeq: "第3款",
        paraName: "镇人民政府应当组织编制村庄规划, 报所在地县（区）人民政府审批。",
        articleId: "b281a28a-12c3-48ec-8cb9-1b70c7e0b958",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "40a51981-468a-4b2d-b064-4a04b15aee26",
        paraSeq: "第1款",
        paraName:
          "镇人民政府组织编制的镇总体规划在报送审批前，应当先经镇人民代表大会审议。镇人民政府应当对代表的审议意见进行研究处理，并反馈处理情况。",
        articleId: "e2221eb2-ebda-4eff-aa81-833b4c0c1a18",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "45eb6cd1-48fb-46c6-afba-f655358bd0ff",
        paraSeq: "第4款",
        paraName: "其他规划文书的时效由审批机关在核发该文书时确定。",
        articleId: "ad2f520b-1b85-4d6b-b4a7-e7c54b6517b9",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "4bb5219e-016b-4b2e-8ba7-cb615fad6a7a",
        paraSeq: "第1款",
        paraName: "主城区的控制性详细规划，由市人民政府城乡规划主管部门组织编制，报市人民政府审批。",
        articleId: "efa86ce2-2364-4a7e-a0a0-2bd16b90e269",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "572075c6-acde-4bfa-8f00-1a4c1e111094",
        paraSeq: "第1款",
        paraName:
          "矿产资源开采企业应当按照以人为本、保护生态、先征后用、先搬后采的原则实施资源开采，并依据城乡规划对采矿区以及塌陷区进行综合治理和生态修复。",
        articleId: "66d5ab56-451e-4dcc-84d1-8d30794da7a9",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "5e3fd9f0-c6c1-4a35-afe4-17f4d423eb0d",
        paraSeq: "第1款",
        paraName:
          "新建、改建、扩建城市道路工程应当根据功能需求同步规划建设地下综合管廊，因特殊原因不能同步的，应当进行管线综合规划设计，统筹各类管线建设。已建成地下综合管廊的道路，不得另行擅自开挖铺设管线。",
        articleId: "5e13057b-0c52-4979-a44e-c183eb001552",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "61300ba8-d1e6-46f0-9103-7877c7ddcaa2",
        paraSeq: "第1款",
        paraName:
          "旧城区改造应当以街区为单元实施整体改造，并与区域内基础设施和公共服务设施承载力相适应；合理确定开发强度，严格控制新建住宅项目，降低人口密度，增加绿地、广场、停车场等公共空间，保护历史建筑和历史文化街区，延续传统风貌，改善人居环境和城市面貌。",
        articleId: "a64c48e5-c122-4872-80e7-d3d4b13abf6a",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "6c74def0-f161-4e79-a632-f9503e426377",
        paraSeq: "第1款",
        paraName:
          "任何单位和个人有权对城乡规划的制定、修改和实施提出意见和建议，有权就涉及其利害关系的建设活动是否符合城乡规划向城乡规划主管部门进行查询，有权对违反城乡规划的行为进行举报和控告。",
        articleId: "2293ce19-273d-489f-88ff-ea5e32aa9992",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "710c6748-8582-4075-be95-27ad79cc7d43",
        paraSeq: "第1款",
        paraName:
          "城乡规划主管部门或者省人民政府确定的镇人民政府在核发建设工程规划许可证前，应当对拟批准的内容进行批前公示，公示期不得少于七日。",
        articleId: "8ea0d651-bee3-4d56-827f-1a9f5e3f3a47",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "7257cb2f-2e25-4546-b2fb-33b5e3d19602",
        paraSeq: "第2款",
        paraName:
          "市、县（区）人民政府的有关部门和镇人民政府、街道办事处应当按照各自职责，协同做好城乡规划管理的相关工作。",
        articleId: "9914868e-9115-482c-9ad0-15976c41055e",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "74b212f4-61e3-4a90-a2ec-84256e6b4751",
        paraSeq: "第1款",
        paraName:
          "风景区的核心区内禁止建设与风景区保护无关的建筑物。风景区周边的建设，应当注重保护自然山体、河湖湿地、水体轮廓线，严格控制建筑密度和建筑高度。",
        articleId: "d3706c27-b079-4103-b47c-141370a4fbb5",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "7b30ef79-4b78-4242-b9b7-67064fe9a1af",
        paraSeq: "第2款",
        paraName:
          "市、县人民政府相关部门制定旧城改造、土地收储供应、保障性住房建设、生态环境建设、城建投资等年度实施计划，应当依据近期建设规划。城乡规划主管部门应当参与年度实施计划的制定。",
        articleId: "408a6cb8-91c4-4eee-b29d-780548884d3b",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "7b70a105-0ab0-43df-84c7-843d58d0e52e",
        paraSeq: "第1款",
        paraName:
          "城乡规划主管部门或者省人民政府确定的镇人民政府对修建性详细规划、建设工程设计方案进行审核，符合规划条件的，应当自收到申请之日起三十日内核发建设工程规划许可证；对不符合要求的，不予核发建设工程规划许可证，书面告知申请人，并说明理由。",
        articleId: "6ff6162f-26bb-4ec0-bd04-3d9cbbd9e814",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "8486a6fe-542c-4f99-8899-74e14d704b95",
        paraSeq: "第2款",
        paraName:
          "县人民政府所在地镇的控制性详细规划，由县人民政府城乡规划主管部门组织编制，报县人民政府审批；城市规划区内镇的控制性详细规划，由镇人民政府组织编制，报市人民政府审批；其他镇的控制性详细规划，由镇人民政府组织编制，报县（区）人民政府审批。",
        articleId: "efa86ce2-2364-4a7e-a0a0-2bd16b90e269",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "857eeb10-e949-4b14-a7a1-e37aeda85a04",
        paraSeq: "第4款",
        paraName: "纳入城市、镇规划建设用地范围内的村庄不再单独编制村庄规划。",
        articleId: "b281a28a-12c3-48ec-8cb9-1b70c7e0b958",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "9553e18c-f328-4d82-84ad-9fb3b8f04721",
        paraSeq: "第1款",
        paraName:
          "城乡规划主管部门应当自收到申请之日起三十日内予以核实；对不符合规划条件和规划许可要求的，发放不予通过的通知并载明不予通过的理由。",
        articleId: "c8bafb48-21c9-42e5-ba82-ece0502f3b1d",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "981748d9-6020-4d80-a516-b513b46fc55a",
        paraSeq: "第1款",
        paraName: "变更内容涉及相关部门审查内容的，受理申请的部门应当征求相关部门的意见。",
        articleId: "bd670265-26b0-4719-8267-150c53ea5470",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "9c2a1a82-d02a-49b9-9458-2637b62e86be",
        paraSeq: "第1款",
        paraName: "建设单位在取得建设用地规划许可证后，方可申请用地。",
        articleId: "6c30c75c-c2c8-4686-82b4-fe331fa1b2f6",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a03c327a-1e87-4d48-8a9e-233f3cd34ce5",
        paraSeq: "第1款",
        paraName:
          "城乡规划督察员对城乡规划违法行为，应当及时向县人民政府及其城乡规划主管部门提出督察意见，同时将督察意见报市人民政府城乡规划主管部门。",
        articleId: "7a533018-f800-45f8-83b9-561e4170adf7",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a2f4b9df-742a-4ccf-8a37-699cbb14e076",
        paraSeq: "第1款",
        paraName:
          "城乡规划委员会由本级人民政府及其相关职能部门代表、专家和公众代表组成，其中专家和公众代表比例不低于四分之一。",
        articleId: "8402c2ce-6f64-42c6-880d-114754005cea",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a438c0c9-8b0a-4a10-b49d-a5850928847a",
        paraSeq: "第1款",
        paraName:
          "市辖区内镇的总体规划由所在地镇人民政府组织编制，位于城市规划区内的报市人民政府审批，位于城市规划区外的报所在地的区人民政府审批。",
        articleId: "b281a28a-12c3-48ec-8cb9-1b70c7e0b958",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a4e49160-5eae-4f02-8cec-2e0e0573c159",
        paraSeq: "第3款",
        paraName:
          "规划要求配置绿地、幼儿园、中小学校、停车场、菜市场、公共交通站场、消防设施、环卫设施、物业管理用房、社区办公服务用房、社区养老等配套设施的城镇建设项目，配套设施应当与主体工程统一设计、同步建设、同时交付使用。",
        articleId: "6ff6162f-26bb-4ec0-bd04-3d9cbbd9e814",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a537c4af-d71d-4ff8-a39a-d5296fc13d10",
        paraSeq: "第2款",
        paraName:
          "因特殊情况需在局部地段临时修建城市市政、公用设施的，应当向城乡规划主管部门申请办理临时建设工程规划许可证。",
        articleId: "782424e9-907d-4ef5-886b-e859dba96192",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "a840dee6-74e0-4a5e-a86e-f377ed0f7a9e",
        paraSeq: "第3款",
        paraName: "变更后的建设工程规划许可证内容及附图应当按照规定予以公布。",
        articleId: "bd670265-26b0-4719-8267-150c53ea5470",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "cb7689f6-c337-49f7-862a-29d630e511be",
        paraSeq: "第2款",
        paraName: "未经核实或者经核实不符合规划条件和规划许可要求的，建设单位不得组织竣工验收。",
        articleId: "c8bafb48-21c9-42e5-ba82-ece0502f3b1d",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "ce9ccf9a-6fba-4c09-bbcc-ce550dec2c8a",
        paraSeq: "第1款",
        paraName:
          "近期建设规划应当以重要基础设施、公共服务设施和中低收入居民住房建设以及生态环境保护为重点内容，并根据当地经济社会发展水平合理确定建设规模和时序。",
        articleId: "408a6cb8-91c4-4eee-b29d-780548884d3b",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "d129c100-6b93-4b30-ad29-472c5a4b023a",
        paraSeq: "第2款",
        paraName:
          "县人民政府所在地镇的总体规划，由县人民政府组织编制，报市人民政府审批。县域内其他镇的总体规划由镇人民政府组织编制，报县人民政府审批。",
        articleId: "b281a28a-12c3-48ec-8cb9-1b70c7e0b958",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "d1f1ce45-867c-46ca-9065-83df51673f3b",
        paraSeq: "第1款",
        paraName:
          "县人民政府城乡规划主管部门负责本辖区内城乡规划管理工作，业务上接受市人民政府城乡规划主管部门的指导和监督。",
        articleId: "9914868e-9115-482c-9ad0-15976c41055e",
        sectId: null,
        chapId: "2a086e2d-1a98-43a1-9e07-6b0e1baaced4",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "d4d830bb-a241-494c-98ad-a894ec72fea6",
        paraSeq: "第2款",
        paraName: "建设项目包含地下工程的，地下工程应当与地上工程同时办理建设工程规划许可证。",
        articleId: "6ff6162f-26bb-4ec0-bd04-3d9cbbd9e814",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "de798bc7-d3ee-45ab-acfb-d16e555f390d",
        paraSeq: "第1款",
        paraName: "市、县人民政府城乡规划主管部门应当设立规划展示固定场所，并配备方便查询的设施、设备。",
        articleId: "eb7d3a47-4d1d-4f9f-a493-d059933be8c3",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "e3a38587-8acf-4b33-8dbe-7e61e2854ac3",
        paraSeq: "第1款",
        paraName: "在城市总体规划、镇总体规划确定的建设用地范围以外，不得设立各类开发区和城市新区。",
        articleId: "3222584c-131b-4f3f-8d17-d4a12c8b3f66",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "e5e7a97b-c553-4259-80be-099b256771cb",
        paraSeq: "第1款",
        paraName:
          "临时建（构）筑物应当设置明显标识，使用期限不得超过二年。临时建筑物不得超过两层，总高度不得超过七米，不得开发地下空间。",
        articleId: "782424e9-907d-4ef5-886b-e859dba96192",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "ec6b210f-56e8-4b60-91b1-9634bc653504",
        paraSeq: "第2款",
        paraName:
          "确需占用农用地的，申请人还应当提供农用地转用批准材料。镇人民政府应当自收到申请之日起十日内提出审查意见，报市、县人民政府城乡规划主管部门。城乡规划主管部门应当自收到审查意见之日起十五日内做出决定，符合村庄规划的，核发乡村建设规划许可证；不符合村庄规划的，不予核发乡村建设规划许可证，书面告知申请人，并说明理由。",
        articleId: "5869f47a-2259-4270-b2a0-291cde2bf283",
        sectId: null,
        chapId: "fc89fa98-cba5-4a22-999e-cbec38359a60",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
      {
        id: "f3c64ee3-154e-4b40-8a1e-c5112cd84ad6",
        paraSeq: "第1款",
        paraName:
          "对涉及违法建设的设计、施工、监理等单位及其直接责任人员，市、县人民政府城乡规划主管部门、城市管理综合执法部门应当及时通报市、县人民政府建设行政主管部门处理。",
        articleId: "2737515a-1683-4e31-8e7c-ec4d00bb45c8",
        sectId: null,
        chapId: "c282cf27-b228-4e10-ab2c-cf02142c78fc",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
    ],
  };
});

Mock.mock("/api/subParaSimple", "get", () => {
  return {
    data: [
      {
        id: "2fdabdc6-3203-4757-9757-96b161b27f5a",
        subparaSeq: "第（2）项",
        subparaName: "（二）容积率、建筑高度、建筑密度、绿地率等指标；",
        paraId: "0916de51-d710-4ffd-a044-6c8f27de79f6",
        articleId: "fea93ecf-c5ee-4a74-bfcb-7f4b4fd7463d",
        sectId: null,
        chapId: "6a198395-fdab-471b-9258-212d1f852ec1",
        partId: null,
        docId: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyN2Q2NWJlMzE1NWI%3D",
      },
    ],
  };
});

Mock.mock("/api/itemSimple", "get", () => {
  return {
    data: [
      {
        id: "0a9cb3cf-edc4-49e2-a288-8e054378b5ce",
        itemSeq: "第4目",
        itemName: "4、带有该商标标识的商品实物照片;",
        subparaId: "2fdabdc6-3203-4757-9757-96b161b27f5a",
        paraId: "2bbefd2d-de83-4b60-a5f5-51f718d8aacd",
        articleId: "3c074fa5-717d-4027-81cc-8015f6b6c58c",
        sect_id: null,
        chap_id: null,
        part_id: null,
        doc_id: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyODg5OWQwNTQwNzE%3D",
      },
      {
        id: "264b4fc0-1a4b-49f3-b844-ae2eacf30fc8",
        itemSeq: "第3目",
        itemName: "3、中介机构、市级以上行业协会或者行业主管部门出具的同行业排名（或者市场占有率）证明;",
        subparaId: "2fdabdc6-3203-4757-9757-96b161b27f5a",
        paraId: "2bbefd2d-de83-4b60-a5f5-51f718d8aacd",
        articleId: "3c074fa5-717d-4027-81cc-8015f6b6c58c",
        sect_id: null,
        chap_id: null,
        part_id: null,
        doc_id: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyODg5OWQwNTQwNzE%3D",
      },
      {
        id: "63ca8a44-cfeb-41e4-b3e8-5a82f8e1ad30",
        itemSeq: "第1目",
        itemName: "1、商品的年销售量、营业收入和净利润等的财务报表;",
        subparaId: "2fdabdc6-3203-4757-9757-96b161b27f5a",
        paraId: "2bbefd2d-de83-4b60-a5f5-51f718d8aacd",
        articleId: "3c074fa5-717d-4027-81cc-8015f6b6c58c",
        sect_id: null,
        chap_id: null,
        part_id: null,
        doc_id: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyODg5OWQwNTQwNzE%3D",
      },
      {
        id: "81d7e841-f883-43d9-be9a-445a062b4a97",
        itemSeq: "第5目",
        itemName: "5、商品销售区域（含出口）的证明材料。",
        subparaId: "2fdabdc6-3203-4757-9757-96b161b27f5a",
        paraId: "2bbefd2d-de83-4b60-a5f5-51f718d8aacd",
        articleId: "3c074fa5-717d-4027-81cc-8015f6b6c58c",
        sect_id: null,
        chap_id: null,
        part_id: null,
        doc_id: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyODg5OWQwNTQwNzE%3D",
      },
      {
        id: "daacf9a8-51e1-4a1e-ac33-294dbba74df7",
        itemSeq: "第2目",
        itemName: "2、税务部门出具的完税证明;",
        subparaId: "2fdabdc6-3203-4757-9757-96b161b27f5a",
        paraId: "2bbefd2d-de83-4b60-a5f5-51f718d8aacd",
        articleId: "3c074fa5-717d-4027-81cc-8015f6b6c58c",
        sect_id: null,
        chap_id: null,
        part_id: null,
        doc_id: "NDAyOGFiY2M2MTI3Nzc5MzAxNjEyODg5OWQwNTQwNzE%3D",
      },
    ],
  };
});

Mock.mock("/api/appendixSimple", "get", () => {
  return {
    data: [],
  };
});
