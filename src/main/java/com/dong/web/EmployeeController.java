package com.dong.web;

import com.dong.domain.AjaxRes;
import com.dong.domain.Employee;
import com.dong.domain.PageListRes;
import com.dong.domain.QueryVo;
import com.dong.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dongly on 2019/7/19
 */

@Controller
public class EmployeeController extends BaseController{

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/employee")
    @RequiresPermissions("employee:index")
    public String employee() {
        return "employee";
    }

    // 分页获取 和 条件查找
    @RequestMapping("/employeeList")
    @ResponseBody
    @RequiresPermissions("employee:index")
    public PageListRes employList(QueryVo vo) {
        //System.out.println(vo);
        PageListRes pageListRes = employeeService.getEmployee(vo);

        return pageListRes;
    }

    // 添加
    @RequestMapping("addEmployee")
    @ResponseBody
    @RequiresPermissions("employee:add")
    public AjaxRes saveEmployee(Employee employee) {
        AjaxRes res = new AjaxRes();
        try {
            employee.setState(true);
            employeeService.addEmployee(employee);
            res.setSuccess(true);
            res.setMsg("保存成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("保存失败");
        }
        return res;
    }

    // 更新
    @RequestMapping("updateEmployee")
    @ResponseBody
    @RequiresPermissions("employee:edit")
    public AjaxRes updateEmployee(Employee employee) {
        System.out.println(employee);
        employee.setState(true);

        AjaxRes res = new AjaxRes();
        try {
            employeeService.updateEmployee(employee);
            res.setSuccess(true);
            res.setMsg("更新成功");
        } catch (Exception e) {
            res.setSuccess(false);
            res.setMsg("更新失败");
        }
        return res;
    }

    // 离职更新
    @RequestMapping("updateState")
    @ResponseBody
    @RequiresPermissions("employee:delete")
    public AjaxRes updateState(Long id) {
        AjaxRes res = new AjaxRes();
        try {
            employeeService.updateState(id);
            res.setSuccess(true);
            res.setMsg("离职更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            res.setSuccess(false);
            res.setMsg("离职更新失败");
        }
        return res;
    }

    // 下载excel
    @RequestMapping("downloadExcel")
    @ResponseBody
    public void downloadExcel(HttpServletResponse response) throws IOException {
        QueryVo vo = new QueryVo();
        vo.setPage(1);
        vo.setRows(10);
        PageListRes res = employeeService.getEmployee(vo);
        List<Employee> employees = (List<Employee>) res.getRows();
        // 创建工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("员工信息");
        HSSFRow row = sheet.createRow(0);   // 创建一行
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("用户名");
        row.createCell(2).setCellValue("真实姓名");
        row.createCell(3).setCellValue("入职日期");
        row.createCell(4).setCellValue("电话");
        row.createCell(5).setCellValue("邮件");
        row.createCell(6).setCellValue("在职");
        // 填写数据
        HSSFRow employeeRow = null;
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            employeeRow = sheet.createRow(i + 1);
            employeeRow.createCell(0).setCellValue(i+1);
            employeeRow.createCell(1).setCellValue(employee.getUsername());
            employeeRow.createCell(2).setCellValue(employee.getRealName());
                if (employee.getInputTime() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String date = dateFormat.format(employee.getInputTime());
                employeeRow.createCell(3).setCellValue(date);
            }
            employeeRow.createCell(4).setCellValue(employee.getTel());
            employeeRow.createCell(5).setCellValue(employee.getEmail());
            if (employee.getState()) {
                employeeRow.createCell(6).setCellValue("是");
            } else {
                employeeRow.createCell(6).setCellValue("否");
            }
        }
        // 下载
        String filename = new String("员工信息.xls".getBytes("utf-8"), "iso8859-1");
        response.setHeader("content-Disposition", "attachment;filename=" + filename);
        workbook.write(response.getOutputStream());
    }

    // 下载Excel模板
    @RequestMapping("downloadExcelTpl")
    @ResponseBody
    public void downloadExcelTpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String filename = new String("员工信息模板.xls".getBytes("utf-8"), "iso8859-1");
        response.setHeader("content-Disposition", "attachment;filename=" + filename);
        // 获取模板文件路径
        String realPath = request.getSession().getServletContext().getRealPath("static/ExcelTml.xls");
        FileInputStream stream = new FileInputStream(realPath);
        IOUtils.copy(stream, response.getOutputStream());
        stream.close();     // 关闭
    }

    // 上传 解析Excel
    @RequestMapping("uploadExcel")
    @ResponseBody
    public AjaxRes uploadExcel(MultipartFile excel) {
        AjaxRes res = new AjaxRes();

        try {
            HSSFWorkbook workbook = new HSSFWorkbook(excel.getInputStream());
            HSSFSheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();     // 获取最大行

            HSSFRow employeeRow = null;
            for (int i = 1; i <= lastRowNum; i++) {
                Employee employee = new Employee();
                employeeRow = sheet.getRow(i);
                employee.setUsername((String) getCellValue(employeeRow.getCell(1)));
                employee.setRealName((String) getCellValue(employeeRow.getCell(2)));
                employee.setInputTime((Date) getCellValue(employeeRow.getCell(3)));
                long tel = (long) (double) getCellValue(employeeRow.getCell(4));
                employee.setTel(String.valueOf(tel));
                employee.setEmail((String) getCellValue(employeeRow.getCell(5)));
                employee.setPassword(employee.getUsername());

                employeeService.addEmployee(employee);      // 添加一条员工数据
            }

            res.setMsg("导入成功");
            res.setSuccess(true);
        } catch (Exception e) {
            res.setMsg("导入失败");
            res.setSuccess(false);
            e.printStackTrace();
        }
        return res;
    }

    // 读取Excel
    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            case STRING:    // 字符串
                return cell.getRichStringCellValue().getString();
            case NUMERIC:   // 数
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue();     // 日期
                } else {
                    return cell.getNumericCellValue();  // 数字
                }
            case BOOLEAN:   // 布尔
                return cell.getBooleanCellValue();
            case FORMULA:   // 公式
                return cell.getCellFormula();
        }
        return cell;
    }

}
