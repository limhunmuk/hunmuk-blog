import{d as b,e as k,f as C,o as i,g as c,a as t,w as e,F as d,u as I,r as s,h as n,i as x,t as u,b as m,_ as V}from"./index-Bu46FCoR.js";import{a as v}from"./authStore-eQVZia_j.js";import"./axios-upsvKRUO.js";const B={class:"inquiry-list"},D=b({__name:"InquiryListView",setup(L){const f=I(),p=k([]),y=async()=>{await v.get("/inquiry").then(a=>{console.log(a),p.value=a.data,console.log("============================="),console.log(a.data[0].title),console.log(a.data[0].content),console.log("=============================")})};C(()=>{y()});const g=()=>{f.push({name:"inquiryCreate"})};return(a,l)=>{const r=s("el-row"),q=s("router-link"),_=s("el-col"),w=s("el-button");return i(),c(d,null,[t(r,{class:"my-3"},{default:e(()=>l[0]||(l[0]=[n("h1",null,"공지사항",-1)])),_:1}),t(r,null,{default:e(()=>[t(_,null,{default:e(()=>[n("ul",B,[l[1]||(l[1]=n("li",{class:"ui-menu"},[n("span",null,"번호"),n("span",null,"제목"),n("span",null,"작성자"),n("span",null,"작성일")],-1)),(i(!0),c(d,null,x(p.value,o=>(i(),c("li",{key:o.id},[n("span",null,u(o.id),1),t(q,{to:{name:"inquiryDetail",params:{inquiryId:o.id}}},{default:e(()=>[m(u(o.title),1)]),_:2},1032,["to"]),n("span",null,u(o.regId),1),n("span",null,u(o.regDt),1)]))),128))])]),_:1})]),_:1}),t(r,{class:"button-row"},{default:e(()=>[t(_,null,{default:e(()=>[t(w,{class:"el-button",type:"primary",onClick:g},{default:e(()=>l[2]||(l[2]=[m("등록")])),_:1})]),_:1})]),_:1})],64)}}}),E=V(D,[["__scopeId","data-v-91968db2"]]);export{E as default};
