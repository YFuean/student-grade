package com.sx.frame;

import com.sx.entity.*;
import com.sx.factory.DAOFactory;
import com.sx.factory.ServiceFactory;
import com.sx.ui.ImgPanel;
import com.sx.utils.ImgThread;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sun.swing.table.DefaultTableCellHeaderRenderer;

import javax.swing.*;
import javax.swing.table.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdminMainFrame extends JFrame{
    private  Admin admin;
    private JPanel rootPanel;
    private ImgPanel topPanel;
    private ImgPanel leftPanel;
    private JButton 教师管理Button;
    private JButton 学生成绩Button;
    private JButton 学生管理Button;
    private JButton 课程管理Button;
    private JLabel avatarLabel;
    private JLabel nameLabel;
    private JLabel accountLabel;
    private JPanel adPanel;
    private JPanel centerPanel;
    private JPanel teacherPanel;
    private JPanel studentGradePanel;
    private JPanel studentPanel;
    private JComboBox<String> departmentBox;
    private JTextField searchTextField;
    private JButton 搜索Button;
    private JButton 批量导入Button;
    private JPanel studentTablePanel;
    private JButton 导出Button;
    private JComboBox teacherDepartmentBox;
    private JButton 添加教师Button;
    private JPanel rightPanel;
    private ImgPanel addTeacherPanel;
    private ImgPanel showTeacherPanel;
    private JPanel teacherTablePanel;
    private JLabel teacherIdLabel;
    private JLabel teacherNameLabel;
    private JLabel teacherDepLabel;
    private JLabel teacherGenLabel;
    private JLabel teacherJobLabel;
    private JLabel teacherEduLabel;
    private JComboBox upTeaDepBox;
    private JComboBox upTeaJobBox;
    private JComboBox upTeaEduBox;
    private JButton updateTeaBtn;
    private JButton preserveTeaBtn;
    private JLabel teacherAvatarLabel;
    private JTextField addTeaIdField;
    private JTextField addTeaNameField;
    private JRadioButton manRadioButton;
    private JRadioButton womanRadioButton;
    private JComboBox addTeaDepBox;
    private JComboBox addTeaJobBox;
    private JComboBox addTeaEduBox;
    private JButton addTeaBtn;
    private JPanel coursePanel;
    private JPanel addCoursePanel;
    private JPanel courseTabelPanel;
    private JTextField addCourNameField;
    private JTextField addCourCreditField;
    private JButton addCourseBtn;
    private JButton 统计Button;
    private JPanel countPanel;
    private JPanel treePanel;
    private JPanel chartPanel;
    private JComboBox<Teacher> chartTeaBox;
    private JButton 首页Button;
    private JPanel firstPanel;
    private JPanel newPanel;
    private JLabel imgLabel;
    private int row;
    private JTable studentTable ,studentGradeTable,teacherTable;
    private String upTeaDep,upTeaJob,upTeaEdu;
    private String addTeaDep,addTeaJob,addTeaEdu;
    private CardLayout cardLayout1;
    private DefaultMutableTreeNode all,courseDef;
    private int chartCourseId;
    private String chartTeacherId;

    public AdminMainFrame(Admin admin) {
        this.admin = admin;
        nameLabel.setText(admin.getName());
        accountLabel.setText(admin.getAccount());
        avatarLabel.setText("<html><img src='"+ admin.getAvatar() +"'></html>");
        setTitle("管理员主界面");
        setContentPane(rootPanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        topPanel.setFileName("top.png");
        topPanel.repaint();
        leftPanel.setFileName("left.png");
        leftPanel.repaint();
        showTeacherPanel.setFileName("showTeacher.png");
        showTeacherPanel.repaint();
        addTeacherPanel.setFileName("addTeacher.png");
        addTeacherPanel.repaint();

        //获取centerPanel,获取的是LayoutManager,向下转型CardLayout
        CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
        cardLayout.show(centerPanel,"Card6");
        centerPanel.setBackground(Color.WHITE);
        ImgThread imgThread = new ImgThread();
        imgThread.setImgLabel(imgLabel);
        new Thread(imgThread).start();
        showNews();
        cardLayout1 = (CardLayout) rightPanel.getLayout();
        首页Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card6");
                centerPanel.setBackground(Color.WHITE);
                newPanel.removeAll();
                showNews();
            }
        });
        教师管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card1");
                centerPanel.setBackground(new Color(238, 230, 208));
                cardLayout1.show(rightPanel,"showTeacher");
                List<Teacher> teacherList = ServiceFactory.getTeacherServiceInstance().selectAll();
                showTeacher(teacherList);
            }
        });
        学生成绩Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card2");
                centerPanel.setBackground(new Color(238, 230, 208));
                List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectAll();
                showStudentGrade(studentGradeVOList);
            }
        });
        学生管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card3");
                centerPanel.setBackground(new Color(238, 230, 208));
                List<Student> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                showStudent(studentList);
            }
        });
        课程管理Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card4");
                centerPanel.setBackground(new Color(238, 230, 208));
                List<Course> courseList = ServiceFactory.getCourseServiceInstance().selectAll();
                showCourse(courseList);
            }
        });
        统计Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(centerPanel,"Card5");
                centerPanel.setBackground(new Color(238, 230, 208));
                List<Course> courseList = ServiceFactory.getCourseServiceInstance().selectAll();
                showTree(courseList);
                List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectAll();
                showChart(studentGradeVOList);
            }
        });
        导出Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File file = new File("D:/QLDownload/allStudentGrade.xlsx");
                if (!file.exists()){
                    try {
                        exportTable(studentGradeTable,file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        批量导入Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("D:/QLDownload"));
                int result = fileChooser.showOpenDialog(rootPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    //选中文件
                    File filePath = fileChooser.getSelectedFile();
                    getDataFromExcel("D:/QLDownload"+File.separator + filePath.getName());
                    List<Student> studentList = ServiceFactory.getStudentServiceInstance().selectAll();
                    showStudent(studentList);
                }
            }
        });
        添加教师Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout1.show(rightPanel,"addTeacher");
                addTeacher();
            }
        });
        addTeaBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String gender = null;
                if (manRadioButton.isSelected()){
                    gender = "男";
                }
                if (womanRadioButton.isSelected()){
                    gender = "女";
                }
                Teacher teacher = new Teacher();
                teacher.setId(addTeaIdField.getText());
                teacher.setPassword("e10adc3949ba59abbe56e057f20f883e");
                teacher.setName(addTeaNameField.getText());
                teacher.setAvatar("https://yfuean-student-manager.oss-cn-shanghai.aliyuncs.com/img/moren.jpg");
                teacher.setDepartment(addTeaDep);
                teacher.setJobTitle(addTeaJob);
                teacher.setEducation(addTeaEdu);
                teacher.setGender(gender);
                int n = ServiceFactory.getTeacherServiceInstance().insertTeacher(teacher);
                if (n == 1) {
                    JOptionPane.showMessageDialog(rootPanel,"新增成功");
                    List<Teacher> teacherList = ServiceFactory.getTeacherServiceInstance().selectAll();
                    showTeacher(teacherList);
                }
            }
        });
        addCourseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String creditStr = addCourCreditField.getText();
                int credit = Integer.valueOf(creditStr);
                Course course = new Course();
                course.setName(addCourNameField.getText());
                course.setCredit(credit);
                ServiceFactory.getCourseServiceInstance().insertCourse(course);

                addCourNameField.setText("");
                addCourCreditField.setText("");
                List<Course> courseList = ServiceFactory.getCourseServiceInstance().selectAll();
                showCourse(courseList);
            }
        });
    }
    private void showNews(){
        GridLayout gridLayout = new GridLayout(7,2,5,10);
        newPanel.setLayout(gridLayout);
        //爬虫
        String url = "http://www.niit.edu.cn/2085/list.htm";
        Connection connection = Jsoup.connect(url);
        Document document = null;
        try {
            document = connection.get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements elements = document.getElementsByClass("news_title");
        for (Element element : elements){
            Element link = element.child(0);
            String titleString = link.text();
            JLabel jLabel = new JLabel(titleString);
            jLabel.setFont(new Font("宋体",Font.BOLD,18));
            newPanel.add(jLabel);
            newPanel.revalidate();
        }
    }
    private void showStudentGrade(List<StudentGradeVO> studentGradeVOList) {
        studentGradeTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        studentGradeTable.setModel(model);
        model.setColumnIdentifiers(new String[]{"id","测试日期","课程","学号","学生姓名","成绩"});
        for (StudentGradeVO studentGradeVO:studentGradeVOList) {
            Object[] object = new Object[]{studentGradeVO.getGradeId(),studentGradeVO.getTestDate(),
                    studentGradeVO.getCourseName(),studentGradeVO.getStudentId(),studentGradeVO.getStudentName(),
                    studentGradeVO.getScore()};
            model.addRow(object);
        }
        //将第一列隐藏头像
        TableColumn tc = studentGradeTable.getColumnModel().getColumn(0);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        //获得表头
        JTableHeader header = studentGradeTable.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        header.setFont(new Font("微软雅黑",Font.PLAIN,28));
        studentGradeTable.setRowHeight(35);
        studentGradeTable.setBackground(new Color(188, 228, 239));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        studentGradeTable.setDefaultRenderer(Object.class,r);
        studentGradeTable.setFont(new Font("微软雅黑",Font.PLAIN,22));
        JScrollPane scrollPane = new JScrollPane(studentGradeTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        studentGradePanel.add(scrollPane);

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("修改");
        item.setFont(new Font("微软雅黑",Font.PLAIN,22));
        jPopupMenu.add(item);
        studentGradeTable.add(jPopupMenu);
        studentGradeTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = studentGradeTable.getSelectedRow();
                if (e.getButton() == 3){
                    jPopupMenu.show(studentGradeTable,e.getX(),e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString =  studentGradeTable.getValueAt(row,0).toString();
                String scoreStr = studentGradeTable.getValueAt(row,5).toString();
                int id = Integer.valueOf(idString).intValue();
                int score =Integer.valueOf(scoreStr);
                int choice = JOptionPane.showConfirmDialog(rootPanel,"确定要修改吗？");
                if (choice == 0){
                    if(row != -1){
                        model.removeRow(row);
                    }
                    Grade grade = new Grade();
                    grade.setId(id);
                    grade.setScore(score);
                    ServiceFactory.getStuGraVOServiceInstance().updateGrade(grade);
                }
            }
        });
    }
    private void showStudent(List<Student> studentList) {
        String[] depTip = {"请选择院系","计算机与软件学院","艺术设计学院","交通工程学院","机械工程学院",
                "电气工程学院","航空工程学院","商务贸易学院","经济管理学院","国际贸易学院"};
        for (int i= 0;i<depTip.length;i++){
            departmentBox.addItem(depTip[i]);
        }
        departmentBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED ){
                    int index = departmentBox.getSelectedIndex();
                    if (index !=0){
                        String departmentName = departmentBox.getItemAt(index);
                        List<Student> studentList = ServiceFactory.getStudentServiceInstance().selectByDepartment(departmentName);
                        studentTable.removeAll();
                        showStudent(studentList);
                    }
                }
            }
        });
        搜索Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> studentList = ServiceFactory.getStudentServiceInstance().selectByKeywords(searchTextField.getText());
                showStudent(studentList);
            }
        });
        studentTablePanel.removeAll();
        studentTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        studentTable.setModel(model);
        model.setColumnIdentifiers(new String[]{"学号","姓名","院系","性别","生日","入学时间","地址"});
        for (Student student:studentList) {
            Object[] object = new Object[]{student.getId(),student.getName(),student.getDepartment(),
                    student.getGender(),student.getBirthday(),student.getAdmissionTime(),student.getAddress()};
            model.addRow(object);
        }
        //获得表头
        JTableHeader header = studentTable.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        header.setFont(new Font("微软雅黑",Font.PLAIN,28));
        studentTable.setRowHeight(35);
        studentTable.setBackground(new Color(188, 228, 239));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        studentTable.setDefaultRenderer(Object.class,r);
        studentTable.setFont(new Font("微软雅黑",Font.PLAIN,22));
        JScrollPane scrollPane = new JScrollPane(studentTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        studentTablePanel.add(scrollPane);

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        item.setFont(new Font("微软雅黑",Font.PLAIN,22));
        jPopupMenu.add(item);
        studentTable.add(jPopupMenu);
        studentTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = studentTable.getSelectedRow();
                if (e.getButton() == 3){
                    jPopupMenu.show(studentTable,e.getX(),e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id =  studentTable.getValueAt(row,0).toString();
                int choice = JOptionPane.showConfirmDialog(studentTable,"确定要删除吗？");
                if (choice == 0){
                    if(row != -1){
                        model.removeRow(row);
                    }
                    ServiceFactory.getStudentServiceInstance().deleteStudent(id);
                }
            }
        });
    }
    private void exportTable(JTable table, File file) throws IOException {
        TableModel model = table.getModel();//得到table的Model
        FileWriter out = new FileWriter(file);

        for (int i = 0; i < model.getColumnCount();i++){
            out.write(model.getColumnName(i) + "\t");
        }
        out.write("\n");
        for (int i = 0;i<model.getRowCount();i++){
            for (int j = 0;j<model.getColumnCount();j++){
                out.write(model.getValueAt(i,j).toString() + "\t");
            }
            out.write("\n");
        }
        out.close();
        JOptionPane.showMessageDialog(rootPanel,"导出成功");
    }
    private void getDataFromExcel(String filePath){
        //判断是否为excel类型文件
        if (!filePath.endsWith(".xls")&&!filePath.endsWith(".xlsx")){
            JOptionPane.showMessageDialog(rootPanel,"文件不是excel类型");
        }
        FileInputStream fis = null;
        Workbook wookbook = null;
        //获取一个绝对地址的流
        try {
            fis = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        try {
//            //2003版本的excel，用.xls结尾
//            wookbook = new HSSFWorkbook(fis);//得到工作簿
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try
        {
            //2007版本的excel，用.xlsx结尾
            wookbook = new XSSFWorkbook(fis);//得到工作簿
        } catch (IOException e2)
        {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        //得到一个工作表
        Sheet sheet = wookbook.getSheetAt(0);
        //获得表头
        Row rowHead = sheet.getRow(0);
        //判断表头是否正确
        if(rowHead.getPhysicalNumberOfCells() != 9)
        {
            JOptionPane.showMessageDialog(rootPanel,"表头的数量不对!");
        }
        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();
        //要获得属性
        int studentId;
        String password;
        String studentName;
        String gender;
        String avatar;
        String department;
        Date birthday;
        String address;
        Date admissionTime;
        //获得所有数据
        for (int i = 1;i<=totalRowNum;i++){
            //获得第i行对象
            Row row = sheet.getRow(i);
            //获得获得第i行第0列的 String类型对象
            Cell cell = row.getCell(0);
            studentId = (int) cell.getNumericCellValue();

            cell = row.getCell(1);
            password = cell.getStringCellValue();

            cell = row.getCell(2);
            studentName = cell.getStringCellValue();

            cell = row.getCell(3);
            gender = cell.getStringCellValue();

            cell = row.getCell(4);
            avatar = cell.getStringCellValue();

            cell = row.getCell(5);
            department = cell.getStringCellValue();

            //获得一个日期类型的数据
            cell = row.getCell(6);
            birthday = cell.getDateCellValue();

            cell = row.getCell(7);
            address = cell.getStringCellValue();

            cell = row.getCell(8);
            admissionTime = cell.getDateCellValue();

            Student student = new Student();
            String id = String.valueOf(studentId);
            student.setId(id);
            student.setPassword(password);
            student.setName(studentName);
            student.setGender(gender);
            student.setAvatar(avatar);
            student.setDepartment(department);
            student.setBirthday(birthday);
            student.setAddress(address);
            student.setAdmissionTime(admissionTime);
            ServiceFactory.getStudentServiceInstance().insertStudent(student);
            studentTable.revalidate();
        }
    }
    private void showTeacher(List<Teacher> teacherList){
        teacherDepartmentBox.removeAllItems();
        String[] tip = {"请选择系部","计算机与软件学院","艺术设计学院","交通工程学院","机械工程学院",
                "电气工程学院","航空工程学院","商务贸易学院","经济管理学院","国际贸易学院","体育部","公共基础课部"};
        for (int i= 0;i<tip.length;i++){
            teacherDepartmentBox.addItem(tip[i]);
        }
        teacherDepartmentBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED ){
                    int index = teacherDepartmentBox.getSelectedIndex();
                    if (index !=0){
                        String departmentName = (String) teacherDepartmentBox.getItemAt(index);
                        List<Teacher> teacherList = ServiceFactory.getTeacherServiceInstance().selectByDepartment(departmentName);
                        teacherTablePanel.removeAll();
                        showTeacher(teacherList);
                    }
                }
            }
        });
        teacherTablePanel.removeAll();
        teacherTable = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        teacherTable.setModel(model);
        model.setColumnIdentifiers(new String[]{"工号","姓名","院系","性别","职称","学历","头像","密码"});
        for (Teacher teacher:teacherList) {
            Object[] object = new Object[]{teacher.getId(),teacher.getName(),teacher.getDepartment(),
                    teacher.getGender(),teacher.getJobTitle(),teacher.getEducation(),teacher.getAvatar(),teacher.getPassword()};
            model.addRow(object);
        }
        //将最后一列隐藏头像
        TableColumn tc = teacherTable.getColumnModel().getColumn(6);
        tc.setMinWidth(0);
        tc.setMaxWidth(0);
        TableColumn td = teacherTable.getColumnModel().getColumn(7);
        td.setMinWidth(0);
        td.setMaxWidth(0);
        //获得表头
        JTableHeader header = teacherTable.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        header.setFont(new Font("微软雅黑",Font.PLAIN,28));
        teacherTable.setRowHeight(35);
        teacherTable.setBackground(new Color(188, 228, 239));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        teacherTable.setDefaultRenderer(Object.class,r);
        teacherTable.setFont(new Font("微软雅黑",Font.PLAIN,22));
        JScrollPane scrollPane = new JScrollPane(teacherTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        teacherTablePanel.add(scrollPane);

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem item = new JMenuItem("删除");
        item.setFont(new Font("微软雅黑",Font.PLAIN,22));
        jPopupMenu.add(item);
        teacherTable.add(jPopupMenu);
        teacherTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout1.show(rightPanel,"showTeacher");
                row = teacherTable.getSelectedRow();
                teacherIdLabel.setText(teacherTable.getValueAt(row,0).toString());
                teacherNameLabel.setText(teacherTable.getValueAt(row,1).toString());
                teacherDepLabel.setText(teacherTable.getValueAt(row,2).toString());
                teacherGenLabel.setText(teacherTable.getValueAt(row,3).toString());
                teacherJobLabel.setText(teacherTable.getValueAt(row,4).toString());
                teacherEduLabel.setText(teacherTable.getValueAt(row,5).toString());
                teacherAvatarLabel.setText("<html><img src='" + teacherTable.getValueAt(row,6).toString() + "'></html>");
                updateTeaBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        teacherDepLabel.setVisible(false);
                        upTeaDepBox.setVisible(true);
                        teacherJobLabel.setVisible(false);
                        upTeaJobBox.setVisible(true);
                        teacherEduLabel.setVisible(false);
                        upTeaEduBox.setVisible(true);

                        upTeaDepBox.removeAllItems();
                        String[] depTip = {"请选择系部","计算机与软件学院","艺术设计学院","交通工程学院","机械工程学院",
                                "电气工程学院","航空工程学院","商务贸易学院","经济管理学院","国际贸易学院","体育部","公共基础课部"};
                        for (int i = 0;i < depTip.length;i++){
                            upTeaDepBox.addItem(depTip[i]);
                        }
                        upTeaDepBox.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                if (e.getStateChange() == ItemEvent.SELECTED){
                                    int upTeaDepIndex = upTeaDepBox.getSelectedIndex();
                                    if (upTeaDepIndex !=0){
                                        upTeaDep = (String) upTeaDepBox.getItemAt(upTeaDepIndex);
                                    }
                                }
                            }
                        });

                        upTeaJobBox.removeAllItems();
                        String[] jobTip = {"请选择职称","助教","讲师","副教授","教授"};
                        for (int i = 0;i < jobTip.length;i++){
                            upTeaJobBox.addItem(jobTip[i]);
                        }
                        upTeaJobBox.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                if (e.getStateChange() == ItemEvent.SELECTED){
                                    int upTeaJobIndex = upTeaJobBox.getSelectedIndex();
                                    if (upTeaJobIndex !=0){
                                        upTeaJob = (String) upTeaJobBox.getItemAt(upTeaJobIndex);
                                    }
                                }
                            }
                        });

                        upTeaEduBox.removeAllItems();
                        String[] eduTip = {"请选择学历","本科","硕士研究生","博士研究生"};
                        for (int i = 0;i < eduTip.length;i++){
                            upTeaEduBox.addItem(eduTip[i]);
                        }
                        upTeaEduBox.addItemListener(new ItemListener() {
                            @Override
                            public void itemStateChanged(ItemEvent e) {
                                if (e.getStateChange() == ItemEvent.SELECTED){
                                    int upTeaEduIndex = upTeaEduBox.getSelectedIndex();
                                    if (upTeaEduIndex !=0){
                                        upTeaEdu = (String) upTeaEduBox.getItemAt(upTeaEduIndex);
                                    }
                                }
                            }
                        });


                    }
                });
                preserveTeaBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Teacher teacher = new Teacher();
                        teacher.setId(teacherIdLabel.getText());
                        teacher.setPassword(teacherTable.getValueAt(row,7).toString());
                        teacher.setName(teacherNameLabel.getText());
                        teacher.setGender(teacherGenLabel.getText());
                        teacher.setDepartment(upTeaDep);
                        teacher.setJobTitle(upTeaJob);
                        teacher.setEducation(upTeaEdu);
                        teacher.setAvatar(teacherTable.getValueAt(row,6).toString());
                        int n = ServiceFactory.getTeacherServiceInstance().updateTeacher(teacher);
                        if (n == 1){
                            model.setValueAt(upTeaDep,row,2);
                            model.setValueAt(upTeaJob,row,4);
                            model.setValueAt(upTeaEdu,row,5);
                            teacherDepLabel.setText(upTeaDep);
                            teacherJobLabel.setText(upTeaJob);
                            teacherEduLabel.setText(upTeaEdu);
                            teacherDepLabel.setVisible(true);
                            upTeaDepBox.setVisible(false);
                            teacherJobLabel.setVisible(true);
                            upTeaJobBox.setVisible(false);
                            teacherEduLabel.setVisible(true);
                            upTeaEduBox.setVisible(false);
                        }
                    }
                });
                if (e.getButton() == 3){
                    jPopupMenu.show(teacherTable,e.getX(),e.getY());
                }
            }
        });
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id =  teacherTable.getValueAt(row,0).toString();
                int choice = JOptionPane.showConfirmDialog(teacherTable,"确定要删除吗？");
                if (choice == 0){
                    if(row != -1){
                        model.removeRow(row);
                    }
                    ServiceFactory.getTeacherServiceInstance().deleteTeacher(id);
                }
            }
        });
    }
    private void addTeacher(){
        addTeaDepBox.removeAllItems();
        String[] depTip = {"请选择系部","计算机与软件学院","艺术设计学院","交通工程学院","机械工程学院",
                "电气工程学院","航空工程学院","商务贸易学院","经济管理学院","国际贸易学院","体育部","公共基础课部"};
        for (int i = 0;i < depTip.length;i++){
            addTeaDepBox.addItem(depTip[i]);
        }
        addTeaDepBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int addTeaDepIndex = addTeaDepBox.getSelectedIndex();
                    if (addTeaDepIndex !=0){
                        addTeaDep = (String) addTeaDepBox.getItemAt(addTeaDepIndex);
                    }
                }
            }
        });

        addTeaJobBox.removeAllItems();
        String[] jobTip = {"请选择职称","助教","讲师","副教授","教授"};
        for (int i = 0;i < jobTip.length;i++){
            addTeaJobBox.addItem(jobTip[i]);
        }
        addTeaJobBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int addTeaJobIndex = addTeaJobBox.getSelectedIndex();
                    if (addTeaJobIndex !=0){
                        addTeaJob = (String) addTeaJobBox.getItemAt(addTeaJobIndex);
                    }
                }
            }
        });

        addTeaEduBox.removeAllItems();
        String[] eduTip = {"请选择职称","本科","硕士研究生","博士研究生"};
        for (int i = 0;i < eduTip.length;i++){
            addTeaEduBox.addItem(eduTip[i]);
        }
        addTeaEduBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int addTeaEudIndex = addTeaEduBox.getSelectedIndex();
                    if (addTeaEudIndex !=0){
                        addTeaEdu = (String) addTeaEduBox.getItemAt(addTeaEudIndex);
                    }
                }
            }
        });
    }
    private void showCourse(List<Course> courseList){
        courseTabelPanel.removeAll();
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        model.setColumnIdentifiers(new String[]{"编号","名称","学分"});
        for (Course course:courseList) {
            Object[] object = new Object[]{course.getId(),course.getName(),course.getCredit()};
            model.addRow(object);
        }
        //获得表头
        JTableHeader header = table.getTableHeader();
        DefaultTableCellHeaderRenderer hr = new DefaultTableCellHeaderRenderer();
        hr.setHorizontalAlignment(JLabel.CENTER);
        header.setDefaultRenderer(hr);
        header.setPreferredSize(new Dimension(header.getWidth(),40));
        header.setFont(new Font("微软雅黑",Font.PLAIN,28));
        table.setRowHeight(35);
        table.setBackground(new Color(188, 228, 239));
        table.setFont(new Font("微软雅黑",Font.PLAIN,22));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);

        JScrollPane scrollPane = new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        courseTabelPanel.add(scrollPane);
        courseTabelPanel.revalidate();//刷新

        JPopupMenu jPopupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("删除");
        deleteItem.setFont(new Font("微软雅黑",Font.PLAIN,22));
        jPopupMenu.add(deleteItem);
        table.add(jPopupMenu);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table.getSelectedRow();
                if (e.getButton() == 3){
                    jPopupMenu.show(table,e.getX(),e.getY());
                }
            }
        });
        deleteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idString = table.getValueAt(row,0).toString();
                int id = Integer.valueOf(idString);
                int choice = JOptionPane.showConfirmDialog(rootPanel,"确定要删除吗？");
                if (choice == 0){
                    if(row != -1){
                        model.removeRow(row);
                    }
                    ServiceFactory.getCourseServiceInstance().deleteCourse(id);
                }
            }
        });
    }
    private void showTree(List<Course> courseList){
        treePanel.removeAll();
        //左边JTree
        all = new DefaultMutableTreeNode("课程");
        for (Course course:courseList){
            courseDef = new DefaultMutableTreeNode(course.getName());
            all.add(courseDef);
            List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByCourseId(course.getId());
            for (StudentGradeVO studentGradeVO:studentGradeVOList) {
                DefaultMutableTreeNode teacherDef = new DefaultMutableTreeNode(studentGradeVO.getTeacherName());
                courseDef.add(teacherDef);
            }
        }
        final JTree courseTree = new JTree(all);
        courseTree.setFont(new Font("微软雅黑",Font.PLAIN,20));
        courseTree.setRowHeight(30);

        JScrollPane scrollPane = new JScrollPane(courseTree,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setOpaque(false);
        treePanel.add(scrollPane);
    }
    private void showChart(List<StudentGradeVO> studentGradeVOList){
        Teacher teaTip = new Teacher();
        teaTip.setName("请选择老师");
        chartTeaBox.addItem(teaTip);
        List<Teacher> teacherList = ServiceFactory.getTeacherServiceInstance().selectAll();
        for (Teacher teacher:teacherList) {
            chartTeaBox.addItem(teacher);
        }

        chartTeaBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED){
                    int index = chartTeaBox.getSelectedIndex();
                    if (index !=0){
                        chartTeacherId = chartTeaBox.getItemAt(index).getId();
                        List<StudentGradeVO> studentGradeVOList = ServiceFactory.getStuGraVOServiceInstance().selectByTeacherId(chartTeacherId);
                        chartPanel.removeAll();
                        showChart(studentGradeVOList);
                    }
                }
            }
        });

        chartPanel.removeAll();
        CategoryDataset dataset = getDataSet(studentGradeVOList);
        JFreeChart chart = ChartFactory.createBarChart3D(
                //图标标题
                "学生成绩",
                //目录轴的显示标签
                "学生姓名",
                //数值轴的显示标签
                "分数",
                //数据集
                dataset,
                // 图表方向：水平、垂直
                PlotOrientation.VERTICAL,
                // 是否显示图例(对于简单的柱状图必须是false)
                true,
                //是否生成工具
                false,
                //是否生成URL链接
                false
        );
        //从这里开始
        CategoryPlot plot = chart.getCategoryPlot();//获取图表区域对象
        CategoryAxis domainAxis=plot.getDomainAxis();//水平底部列表
        domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14));//水平底部标
        domainAxis.setTickLabelFont(new Font("黑体",Font.BOLD,14));//垂直标题
        ValueAxis rangeAxis=plot.getRangeAxis();//获取柱状
        rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("黑体",Font.BOLD,20));//设置标题字体

        ChartPanel panel = new ChartPanel(chart,true);
        chartPanel.add(panel);
    }
    private static CategoryDataset getDataSet(List<StudentGradeVO> studentGradeVOList) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (StudentGradeVO studentGradeVO:studentGradeVOList) {
            dataset.addValue(studentGradeVO.getScore(),studentGradeVO.getCourseName(),studentGradeVO.getStudentName());
        }
        return dataset;
    }

    //public static void main(String[] args) throws Exception{ new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("")); }
    public static void main(String[] args) throws Exception{
        new AdminMainFrame(DAOFactory.getAdminDAOInstance().getAdminByAccount("yuefan@qq.com")); }
}
