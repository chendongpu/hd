webpackJsonp([3],{ELaS:function(e,t){},UgNS:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"bg"},[e.editShow?e._e():a("div",{staticClass:"content"},[a("Search",{attrs:{user:"true"}}),e._v(" "),a("el-table",{staticStyle:{width:"100%","margin-top":"0.5rem"},attrs:{data:e.tableData,"cell-style":{fontSize:"0.65rem"},"header-cell-style":{background:"#eef1f6",color:"#606266",fontSize:"0.6rem"}}},[a("el-table-column",{attrs:{align:"center",label:"会员ID"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v("98")])]}}],null,!1,1784404586)}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"会员"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("div",{staticClass:"row"},[a("img",{attrs:{src:"http://t8.baidu.com/it/u=3571592872,3353494284&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1581766834&t=b367d43ed4ab7e981ce884ba40843051"}}),a("span",{staticClass:"pl10"},[e._v(e._s(t.row.name))])])]}}],null,!1,818740388)}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"常用收货地址"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.address))])]}}],null,!1,1569047600)}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"积分/余额"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("p",[e._v("积分："+e._s(t.row.num1))]),e._v(" "),a("p",[e._v("余额："+e._s(t.row.num2))])]}}],null,!1,3871446974)}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"成交"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("p",[e._v("订单："+e._s(t.row.num1))]),e._v(" "),a("p",[e._v("金额："+e._s(t.row.num2))])]}}],null,!1,1459254536)}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"注册时间"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("span",[e._v(e._s(t.row.date))])]}}],null,!1,1531225394)}),e._v(" "),a("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.hEdit(1)}}},[e._v("编辑")]),e._v(" "),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.handleDelete(t.$index)}}},[e._v("删除")])]}}],null,!1,3772097454)})],1),e._v(" "),a("div",{staticClass:"p20 textRight"},[a("el-pagination",{attrs:{"current-page":e.currentPage1,"page-size":100,layout:"total,prev, pager, next, jumper",total:1e3},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange,"update:currentPage":function(t){e.currentPage1=t},"update:current-page":function(t){e.currentPage1=t}}})],1)],1),e._v(" "),e.editShow?a("div",{staticClass:"content p20"},[a("div",{staticClass:"blue mb20 back",on:{click:e.back}},[a("i",{staticClass:"iconfont icon-cs-fh-1 "}),a("a",{staticClass:"font18 pl20"},[e._v("返回")])]),e._v(" "),a("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm p20",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[a("el-form-item",{attrs:{label:"会员",required:""}},[a("img",{attrs:{src:"http://t8.baidu.com/it/u=3571592872,3353494284&fm=79&app=86&size=h300&n=0&g=4n&f=jpeg?sec=1581766834&t=b367d43ed4ab7e981ce884ba40843051"}}),e._v(" "),a("span",{staticClass:"pl10"},[e._v(e._s(e.ruleForm.name))])]),e._v(" "),a("el-form-item",{attrs:{label:"openId",prop:"name"}},[a("span",{staticClass:"blue"},[e._v("wap_user_3_19906722716")])]),e._v(" "),a("el-form-item",{attrs:{label:"真实姓名"}},[a("el-input",{model:{value:e.ruleForm.name,callback:function(t){e.$set(e.ruleForm,"name",t)},expression:"ruleForm.name"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"微信号"}},[a("el-input",{model:{value:e.ruleForm.nickname,callback:function(t){e.$set(e.ruleForm,"nickname",t)},expression:"ruleForm.nickname"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"手机号码"}},[a("el-input",{model:{value:e.ruleForm.tel,callback:function(t){e.$set(e.ruleForm,"tel",t)},expression:"ruleForm.tel"}})],1),e._v(" "),a("el-form-item",{attrs:{label:"用户密码",required:""}},[a("span",{staticClass:"lightBlue pl10"},[e._v("修改")])]),e._v(" "),a("el-form-item",{attrs:{label:"积分"}},[a("span",{staticClass:"blue"},[e._v("0")]),e._v(" "),a("span",{staticClass:"lightBlue pl10"},[e._v("修改")])]),e._v(" "),a("el-form-item",{attrs:{label:"余额"}},[a("span",{staticClass:"blue"},[e._v("0")]),e._v(" "),a("span",{staticClass:"lightBlue pl10"},[e._v("修改")])]),e._v(" "),a("el-form-item",{attrs:{label:"注册时间"}},[a("span",[e._v("2020-01-21 16:36:49")])]),e._v(" "),a("el-form-item",{attrs:{label:"黑名单",prop:"resource"}},[a("el-radio-group",{model:{value:e.ruleForm.resource,callback:function(t){e.$set(e.ruleForm,"resource",t)},expression:"ruleForm.resource"}},[a("el-radio",{attrs:{label:"是"}}),e._v(" "),a("el-radio",{attrs:{label:"否"}})],1),e._v(" "),a("span",{staticClass:"c9 font10 pl20 "},[e._v("注：设置黑名单后，此会员无法访问该系统")])],1),e._v(" "),a("el-form-item",[a("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("提交")]),e._v(" "),a("el-button",{on:{click:e.back}},[e._v("返回列表")])],1)],1)],1):e._e()])},staticRenderFns:[]}},"e/kJ":function(e,t,a){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var l=a("7biW"),n=a.n(l);t.default={data:function(){return{tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄",num1:1,num2:1},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄",num1:1,num2:1},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄",num1:1,num2:1},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄",num1:1,num2:1}],currentPage1:5,currentPage2:5,currentPage3:5,currentPage4:4,editShow:!1,ruleForm:{name:"",nickname:"",tel:"",resource:""},rules:{name:[{required:!0,message:"请输入真实姓名",trigger:"blur"},{min:3,max:5,message:"长度在 3 到 5 个字符",trigger:"blur"}],tel:[{required:!0,message:"请输入电话号码",trigger:"change"}],nickname:[{required:!0,message:"请输入微信号码",trigger:"change"}]}}},methods:{hEdit:function(e){console.log(e);this.editShow=!0},handleDelete:function(e,t){console.log(e,t)},handleSizeChange:function(e){console.log("每页 "+e+" 条")},handleCurrentChange:function(e){console.log("当前页: "+e)},back:function(){this.editShow=!1},submitForm:function(e){this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;alert("submit!")})},resetForm:function(e){this.$refs[e].resetFields()}},components:{Search:n.a}}},tVh0:function(e,t,a){a("ELaS");var l=a("VU/8")(a("e/kJ"),a("UgNS"),"data-v-b707c312",null);e.exports=l.exports}});
//# sourceMappingURL=3.b64b3679b7ca2bc21d24.js.map