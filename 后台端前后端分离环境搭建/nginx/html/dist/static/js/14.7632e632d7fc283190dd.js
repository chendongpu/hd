webpackJsonp([14],{DtjE:function(e,t){e.exports={render:function(){var e=this,t=e.$createElement,l=e._self._c||t;return l("div",{staticClass:"bg"},[e.editShow?e._e():l("div",{staticClass:"content"},[l("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.hEdit(0)}}},[e._v("+添加添加新直播间")]),e._v(" "),l("el-table",{staticStyle:{width:"100%","margin-top":"0.5rem"},attrs:{data:e.tableData,"cell-style":{fontSize:"0.65rem"},"header-cell-style":{background:"#eef1f6",color:"#606266",fontSize:"0.6rem"}}},[l("el-table-column",{attrs:{align:"center",label:"ID"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v("2")])]}}],null,!1,3168441561)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"显示顺序"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v("2")])]}}],null,!1,3168441561)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"直播间名称"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("div",{staticClass:"row"},[l("img",{attrs:{src:"http://a3.att.hudong.com/68/61/300000839764127060614318218_950.jpg"}}),e._v(" "),l("span",[e._v("医生1")])])]}}],null,!1,996557349)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"直播间分类"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("span",[e._v("分类")])]}}],null,!1,3633751222)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"热门"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-tag",{attrs:{type:"success"}},[e._v("是")])]}}],null,!1,1809013897)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"推荐"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-tag",{attrs:{type:"success"}},[e._v("是")])]}}],null,!1,1809013897)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"状态"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-tag",[e._v("显示")])]}}],null,!1,44275829)}),e._v(" "),l("el-table-column",{attrs:{align:"center",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[l("el-button",{attrs:{size:"mini"},on:{click:function(t){return e.hEdit(1)}}},[e._v("编辑")]),e._v(" "),l("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(l){return e.handleDelete(t.$index)}}},[e._v("删除")])]}}],null,!1,3772097454)})],1),e._v(" "),l("div",{staticClass:"p20 textRight"},[l("el-pagination",{attrs:{"current-page":e.currentPage4,"page-sizes":[100,200,300,400],"page-size":100,layout:"total, sizes, prev, pager, next, jumper",total:400},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}})],1)],1),e._v(" "),e.editShow?l("div",{staticClass:"content p20"},[l("div",{staticClass:"blue mb20  back",on:{click:e.back}},[l("i",{staticClass:"iconfont icon-cs-fh-1 "}),l("a",{staticClass:"font18 pl20"},[e._v("返回")])]),e._v(" "),l("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm p20",attrs:{model:e.ruleForm,rules:e.rules,"label-width":"100px"}},[l("el-form-item",{attrs:{label:"排序"}},[l("el-input",{model:{value:e.ruleForm.nickname,callback:function(t){e.$set(e.ruleForm,"nickname",t)},expression:"ruleForm.nickname"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"名称"}},[l("el-input",{model:{value:e.ruleForm.nickname,callback:function(t){e.$set(e.ruleForm,"nickname",t)},expression:"ruleForm.nickname"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"分类"}},[l("el-select",[l("el-option",[e._v("11")])],1)],1),e._v(" "),l("el-form-item",{attrs:{label:"直播时间"}},[l("div",{staticClass:"row"},[l("span",[e._v("开播时间")]),e._v(" "),l("el-date-picker",{attrs:{type:"datetime",placeholder:"选择日期时间"}})],1),e._v(" "),l("p",{staticClass:"c9 font12"},[e._v("直播时间只用于前台显示，具体直播时间以操作台【开始直播】为准")])]),e._v(" "),l("el-form-item",{attrs:{label:"分类图片"}},[l("el-upload",{staticClass:"upload-demo",attrs:{action:"https://jsonplaceholder.typicode.com/posts/","on-preview":e.handlePreview,"on-remove":e.handleRemove,"before-remove":e.beforeRemove,multiple:"",limit:3,"on-exceed":e.handleExceed,"file-list":e.fileList}},[l("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),e._v(" "),l("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过500kb")])],1),e._v(" "),l("p",{staticClass:"c9 font12"},[e._v("第一张为缩略图，其他为详情页图片，尺寸宽度建议为604，并保持图片大小一致")])],1),e._v(" "),l("el-form-item",{attrs:{label:"平台类型"}},[l("el-radio",{attrs:{label:"1"}},[e._v("智能摄像头")]),e._v(" "),l("el-radio",{attrs:{label:"2"}},[e._v("第三方直播平台")]),e._v(" "),l("el-radio",{attrs:{label:"3"}},[e._v("行业解决方案")])],1),e._v(" "),l("el-form-item",{attrs:{label:"显示方式"}},[l("el-radio",{attrs:{label:"1"}},[e._v("半屏")]),e._v(" "),l("el-radio",{attrs:{label:"2"}},[e._v("全屏")])],1),e._v(" "),l("el-form-item",{attrs:{label:"直播平台"}},[l("el-input",{model:{value:e.ruleForm.nickname,callback:function(t){e.$set(e.ruleForm,"nickname",t)},expression:"ruleForm.nickname"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"直播地址"}},[l("el-input",{model:{value:e.ruleForm.nickname,callback:function(t){e.$set(e.ruleForm,"nickname",t)},expression:"ruleForm.nickname"}})],1),e._v(" "),l("el-form-item",{attrs:{label:"封面类型"}},[l("el-radio",{attrs:{label:"1"}},[e._v("自动抓取直播封面")]),e._v(" "),l("el-radio",{attrs:{label:"2"}},[e._v("自定义封面")])],1),e._v(" "),l("el-form-item",{attrs:{label:"封面"}},[l("el-upload",{staticClass:"upload-demo",attrs:{action:"https://jsonplaceholder.typicode.com/posts/","on-preview":e.handlePreview,"on-remove":e.handleRemove,"before-remove":e.beforeRemove,multiple:"",limit:3,"on-exceed":e.handleExceed,"file-list":e.fileList}},[l("el-button",{attrs:{size:"small",type:"primary"}},[e._v("点击上传")]),e._v(" "),l("div",{staticClass:"el-upload__tip",attrs:{slot:"tip"},slot:"tip"},[e._v("只能上传jpg/png文件，且不超过500kb")])],1),e._v(" "),l("p",{staticClass:"c9 font12"},[e._v("第一张为缩略图，其他为详情页图片，尺寸宽度建议为604，并保持图片大小一致")])],1),e._v(" "),l("el-form-item",{attrs:{label:"推荐"}},[l("el-radio",{attrs:{label:"1"}},[e._v("是")]),e._v(" "),l("el-radio",{attrs:{label:"2"}},[e._v("否")])],1),e._v(" "),l("el-form-item",{attrs:{label:"热门"}},[l("el-radio",{attrs:{label:"1"}},[e._v("是")]),e._v(" "),l("el-radio",{attrs:{label:"2"}},[e._v("否")])],1),e._v(" "),l("el-form-item",{attrs:{label:"状态"}},[l("el-radio",{attrs:{label:"1"}},[e._v("显示")]),e._v(" "),l("el-radio",{attrs:{label:"2"}},[e._v("关闭")])],1),e._v(" "),l("el-form-item",[l("el-button",{attrs:{type:"primary"},on:{click:function(t){return e.submitForm("ruleForm")}}},[e._v("提交")]),e._v(" "),l("el-button",{on:{click:e.back}},[e._v("返回列表")])],1)],1)],1):e._e()])},staticRenderFns:[]}},G3rW:function(e,t,l){l("pDd7");var a=l("VU/8")(l("vuRb"),l("DtjE"),"data-v-350343e8",null);e.exports=a.exports},pDd7:function(e,t){},vuRb:function(e,t,l){"use strict";Object.defineProperty(t,"__esModule",{value:!0});var a=l("7biW"),n=l.n(a),r=l("mBlC"),o=l.n(r);t.default={data:function(){return{isClear:!1,detail:"",tableData:[{date:"2016-05-02",name:"王小虎",address:"上海市普陀区金沙江路 1518 弄",num1:1,num2:1},{date:"2016-05-04",name:"王小虎",address:"上海市普陀区金沙江路 1517 弄",num1:1,num2:1},{date:"2016-05-01",name:"王小虎",address:"上海市普陀区金沙江路 1519 弄",num1:1,num2:1},{date:"2016-05-03",name:"王小虎",address:"上海市普陀区金沙江路 1516 弄",num1:1,num2:1}],currentPage1:5,currentPage2:5,currentPage3:5,currentPage4:4,editShow:!1,ruleForm:{name:"",nickname:"",tel:"",resource:""},rules:{name:[{required:!0,message:"请输入真实姓名",trigger:"blur"},{min:3,max:5,message:"长度在 3 到 5 个字符",trigger:"blur"}],tel:[{required:!0,message:"请输入电话号码",trigger:"change"}],nickname:[{required:!0,message:"请输入微信号码",trigger:"change"}]},options:[{value:"选项1",label:"分类1"},{value:"选项2",label:"分类1"},{value:"选项3",label:"分类1"},{value:"选项4",label:"分类1"}],value:""}},methods:{change:function(e){console.log(e)},hEdit:function(e){console.log(e);this.editShow=!0},handleDelete:function(e,t){console.log(e,t)},handleSizeChange:function(e){console.log("每页 "+e+" 条")},handleCurrentChange:function(e){console.log("当前页: "+e)},back:function(){this.editShow=!this.editShow},submitForm:function(e){this.$refs[e].validate(function(e){if(!e)return console.log("error submit!!"),!1;alert("submit!")})},resetForm:function(e){this.$refs[e].resetFields()},handleRemove:function(e,t){console.log(e,t)},handlePreview:function(e){console.log(e)},handleExceed:function(e,t){this.$message.warning("当前限制选择 3 个文件，本次选择了 "+e.length+" 个文件，共选择了 "+(e.length+t.length)+" 个文件")},beforeRemove:function(e,t){return this.$confirm("确定移除 "+e.name+"？")}},components:{Search:n.a,EditorBar:o.a}}}});
//# sourceMappingURL=14.7632e632d7fc283190dd.js.map