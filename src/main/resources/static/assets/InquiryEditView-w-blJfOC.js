import{d as V,r as w,o as x,c as I,b as e,w as l,F as b,u as g,f as a,g as C,h as p}from"./index-X4FfCd29.js";import{a as c}from"./authStore-IgGJ6nIm.js";import"./axios-upsvKRUO.js";const F=V({__name:"InquiryEditView",props:{inquiryId:{}},setup(_){const s=g(),t=w({}),u=_;x(()=>{console.log("수정화면입니다 >>>"+u.inquiryId),c.get("/inquiry/"+u.inquiryId).then(n=>{console.log(n),t.value=n.data})});const f=()=>{c.put("/inquiry/"+u.inquiryId,{title:t.value.title,content:t.value.content}).then(n=>{console.log(n),alert("수정 되었습니다."),s.replace("/inquiry")})},y=()=>{s.replace("/inquiry")};return(n,o)=>{const q=a("el-row"),m=a("el-input"),i=a("el-form-item"),d=a("el-button"),v=a("el-form");return C(),I(b,null,[e(q,{class:"my-3 mt-3"}),e(v,{model:t.value,"label-width":"auto",style:{"max-width":"600px"}},{default:l(()=>[e(i,{label:"제목"},{default:l(()=>[e(m,{modelValue:t.value.title,"onUpdate:modelValue":o[0]||(o[0]=r=>t.value.title=r)},null,8,["modelValue"])]),_:1}),e(i,{label:"내용"},{default:l(()=>[e(m,{modelValue:t.value.content,"onUpdate:modelValue":o[1]||(o[1]=r=>t.value.content=r),type:"textarea",rows:"15"},null,8,["modelValue"])]),_:1}),e(i,null,{default:l(()=>[e(d,{type:"primary",onClick:f},{default:l(()=>o[2]||(o[2]=[p("저장")])),_:1}),e(d,{onClick:y},{default:l(()=>o[3]||(o[3]=[p("취소")])),_:1})]),_:1})]),_:1},8,["model"])],64)}}});export{F as default};
