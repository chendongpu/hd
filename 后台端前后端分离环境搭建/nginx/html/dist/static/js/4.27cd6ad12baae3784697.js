webpackJsonp([4],{"9hMo":function(t,s,a){"use strict";Object.defineProperty(s,"__esModule",{value:!0});var e=a("7biW"),i=a.n(e);s.default={data:function(){return{tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄",num1:1,num2:1},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄",num1:1,num2:1},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄",num1:1,num2:1},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄",num1:1,num2:1}],currentPage1:5,currentPage2:5,currentPage3:5,currentPage4:4,editShow:!1,activeName:"all",ruleForm:{name:"",nickname:"",tel:"",resource:""},rules:{name:[{required:!0,message:"请输入真实姓名",trigger:"blur"},{min:3,max:5,message:"长度在 3 到 5 个字符",trigger:"blur"}],tel:[{required:!0,message:"请输入电话号码",trigger:"change"}],nickname:[{required:!0,message:"请输入微信号码",trigger:"change"}]}}},methods:{hEdit:function(t){console.log(t);this.editShow=!0},handleDelete:function(t,s){console.log(t,s)},handleSizeChange:function(t){console.log("每页 "+t+" 条")},handleCurrentChange:function(t){console.log("当前页: "+t)},back:function(){this.editShow=!this.editShow},submitForm:function(t){this.$refs[t].validate(function(t){if(!t)return console.log("error submit!!"),!1;alert("submit!")})},resetForm:function(t){this.$refs[t].resetFields()},handleClick:function(t,s){console.log(t,s),this.activeName=t.name}},components:{Search:i.a}}},QY95:function(t,s,a){a("x5V6");var e=a("VU/8")(a("9hMo"),a("tNaT"),"data-v-ccaac6a2",null);t.exports=e.exports},tNaT:function(t,s){t.exports={render:function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"bg"},[t.editShow?t._e():a("div",{staticClass:"content"},[a("Search",{attrs:{comment:"true"}}),t._v(" "),a("el-tabs",{staticClass:"p10",attrs:{type:"card"},on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(s){t.activeName=s},expression:"activeName"}},[a("el-tab-pane",{attrs:{label:"全部",name:"all"}}),t._v(" "),a("el-tab-pane",{attrs:{label:"待付款",name:"waitPay"}}),t._v(" "),a("el-tab-pane",{attrs:{label:"待发货",name:"send"}}),t._v(" "),a("el-tab-pane",{attrs:{label:"待收货",name:"receive"}}),t._v(" "),a("el-tab-pane",{attrs:{label:"维权订单",name:"refund"}}),t._v(" "),a("el-tab-pane",{attrs:{label:"已完成",name:"finish"}}),t._v(" "),a("el-tab-pane",{attrs:{label:"已关闭",name:"close"}})],1),t._v(" "),a("div",{staticClass:"list"},[t._m(0),t._v(" "),t._l(t.tableData,function(s,e){return a("div",{staticClass:"listContent"},[t._m(1,!0),t._v(" "),a("div",{staticClass:"rowTop"},[t._m(2,!0),t._v(" "),t._m(3,!0),t._v(" "),t._m(4,!0),t._v(" "),a("div",{staticClass:"col1"},[t._v("\r\n                        ￥0.01\r\n                    ")]),t._v(" "),a("div",{staticClass:"col1"},[a("p",{staticClass:"mb5"},[t._v("待发货")]),t._v(" "),a("p",[a("el-button",{attrs:{type:"primary",size:"mini"}},[t._v("确认发货")])],1)]),t._v(" "),a("div",{staticClass:"col2"},[a("el-button",{attrs:{size:"mini"},on:{click:function(s){return t.hEdit(1)}}},[t._v("查看详情")])],1)])])})],2),t._v(" "),a("div",{staticClass:"p20 textRight"},[a("el-pagination",{attrs:{"current-page":t.currentPage4,"page-sizes":[100,200,300,400],"page-size":100,layout:"total, sizes, prev, pager, next, jumper",total:400},on:{"size-change":t.handleSizeChange,"current-change":t.handleCurrentChange}})],1)],1),t._v(" "),t.editShow?a("div",{staticClass:"content p20"},[a("div",{staticClass:"blue mb20 back ",on:{click:t.back}},[a("i",{staticClass:"iconfont icon-cs-fh-1 "}),a("a",{staticClass:"font18 pl20"},[t._v("返回")])]),t._v(" "),a("el-steps",{attrs:{active:2,"align-center":""}},[a("el-step",{attrs:{title:"已付款",description:""}}),t._v(" "),a("el-step",{attrs:{title:"待发货",description:"买家已付款，等待发货"}}),t._v(" "),a("el-step",{attrs:{title:"待收货",description:"物流已在运输中"}}),t._v(" "),a("el-step",{attrs:{title:"已完成",description:"订单已完成"}})],1),t._v(" "),a("h3",{staticClass:"mb30"},[t._v("订单信息")]),t._v(" "),t._m(5),t._v(" "),t._m(6),t._v(" "),t._m(7),t._v(" "),t._m(8),t._v(" "),t._m(9),t._v(" "),a("h3",{staticClass:"mt30 mb10"},[t._v("商品信息")]),t._v(" "),t._m(10),t._v(" "),t._m(11),t._v(" "),t._m(12)],1):t._e()])},staticRenderFns:[function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"header row"},[a("div",{staticClass:"col3"},[t._v("商品")]),t._v(" "),a("div",{staticClass:"col2"},[t._v("买家")]),t._v(" "),a("div",{staticClass:"col1"},[t._v("支付/配送")]),t._v(" "),a("div",{staticClass:"col1"},[t._v("价格")]),t._v(" "),a("div",{staticClass:"col1"},[t._v("状态")]),t._v(" "),a("div",{staticClass:"col2"},[t._v("操作")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"row title"},[s("i",{staticClass:"el-iconn-time c9"}),s("span",[this._v("2020.09.08  12:23:43")]),s("span",{staticClass:"pl10"},[this._v("订单编号：djshdfsjdhfsjdjcnsn")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"col3 row"},[s("img",{staticClass:"goodsImg",attrs:{src:"http://yidaodianshang.yidaoit.net/attachment/images/3/2019/11/x0B9XyBCG3x9j9ogUbOXxOyoGu305u.jpg"}}),this._v(" "),s("p",{staticClass:"columnLeft"},[s("span",{staticClass:"textoverflow"},[this._v("商品名称商品名称商品名称商品名称商品名称商品名称商品名称")]),s("span",{staticClass:"c9 font12"},[this._v("商品规格")])])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"col2"},[s("p",{staticClass:"mb5"},[this._v("XXX")]),this._v(" "),s("span",[this._v("18567676545654")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"col1"},[s("p",{staticClass:"mb5"},[this._v("微信支付")]),this._v(" "),s("p",[this._v("快递")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"row mt10"},[s("span",{staticClass:"col1 c9"},[this._v("订单编号：")]),s("span",[this._v("jdjsdjsjdjsdjdsj")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"row mt10"},[s("span",{staticClass:"col1 c9"},[this._v("付款方式：")]),s("span",[this._v("余额支付")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"row mt10"},[s("span",{staticClass:"col1 c9"},[this._v("配送方式：")]),s("span",[this._v("快递")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"row mt10"},[s("span",{staticClass:"col1 c9"},[this._v("收货人：")]),s("span",[this._v("浙江省杭州市萧山区 xxx 182754548583")])])},function(){var t=this.$createElement,s=this._self._c||t;return s("div",{staticClass:"row mt10"},[s("span",{staticClass:"col1 c9"},[this._v("订单状态：")]),s("span",[this._v("待发货")])])},function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"header row"},[a("div",{staticClass:"col3"},[t._v("商品")]),t._v(" "),a("div",{staticClass:"col2"},[t._v("规格")]),t._v(" "),a("div",{staticClass:"col2"},[t._v("单价")]),t._v(" "),a("div",{staticClass:"col1"},[t._v("数量")]),t._v(" "),a("div",{staticClass:"col2"},[t._v("原价")])])},function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"listContent "},[a("div",{staticClass:"row pr5"},[a("div",{staticClass:"col3 row"},[a("img",{staticClass:"goodsImg",attrs:{src:"http://yidaodianshang.yidaoit.net/attachment/images/3/2019/11/x0B9XyBCG3x9j9ogUbOXxOyoGu305u.jpg"}}),t._v(" "),a("p",{staticClass:"columnLeft"},[a("span",{staticClass:"textoverflow"},[t._v("商品名称商品名称商品名称商品名称商品名称商品名称商品名称")])])]),t._v(" "),a("div",{staticClass:"col2"},[a("span",[t._v("商品规格")])]),t._v(" "),a("div",{staticClass:"col2"},[a("span",[t._v("￥1.00")])]),t._v(" "),a("div",{staticClass:"col1"},[a("span",[t._v("X1")])]),t._v(" "),a("div",{staticClass:"col2"},[a("span",[t._v("￥1.00")])])])])},function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"columnRight font12"},[a("p",{staticClass:"mb5"},[a("span",{staticClass:"c9"},[t._v("商品小计：")]),a("span",[t._v("￥1.00")])]),t._v(" "),a("p",{staticClass:"mb5"},[a("span",{staticClass:"c9"},[t._v("运费")]),a("span",[t._v("￥1.00")])]),t._v(" "),a("p",{staticClass:"mb5"},[a("span",{staticClass:"c9"},[t._v("实付款")]),a("span",{staticClass:"red"},[t._v("￥1.00")])])])}]}},x5V6:function(t,s){}});
//# sourceMappingURL=4.27cd6ad12baae3784697.js.map