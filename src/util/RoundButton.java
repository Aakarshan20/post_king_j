package util;
import java.awt.*;
import javax.swing.*;
public class RoundButton extends JButton {
	 
    public RoundButton(String label) {
      super(label); // 调用父类构造函数
      setContentAreaFilled(false); // 不自行绘制按钮背景
    }
 
    // 绘制圆和标签
    protected void paintComponent(Graphics g) {
      if (getModel().isArmed()) { // 鼠标点击时
          g.setColor(Color.lightGray); // 颜色为灰色
      } else {
          g.setColor(getBackground()); // 置按钮颜色
      }
      g.fillOval(0, 0, getSize().width, getSize().height); // 绘制圆
      super.paintComponent(g); // 调用父类函数,绘制其余部分
    }
 
    // 绘制边框
    protected void paintBorder(Graphics g) {
      g.setColor(getForeground()); // 设置边框颜色
      g.drawOval(0, 0, getSize().width - 1, getSize().height - 1); // 在边界上绘制一个椭圆
    }
}
/*
使用範例
public class RoundButtonDemo extends JFrame {
 
    private int clickCount = 0; // 记录安钮的点击次数
    private JButton button1;
    private JButton button2;
 
    public RoundButtonDemo() {
      button1 = new RoundButton("这是一个圆形按钮"); // 初始化按钮一
      Dimension dim = button1.getPreferredSize(); // 得到按钮一的最佳尺寸
      double maxsize = Math.max(dim.getHeight(), dim.getWidth()); // 得到长宽中的最大值
      dim.setSize(maxsize, maxsize); // 更改长宽为长宽中的最大值
      button1.setPreferredSize(dim); // 设置最佳尺寸
      button2 = new RoundButton("点击了: " + clickCount + " 次"); // 初始化按钮二
      button1.setBackground(Color.blue); // 设置按钮的背景颜色
      button2.setBackground(Color.pink);
      getContentPane().add(button1); // 增加组件
      getContentPane().add(button2);
      getContentPane().setLayout(new FlowLayout()); // 设置布局管理器
      button2.addActionListener(new ActionListener() { // 铵钮二的事件处理
                  public void actionPerformed(ActionEvent e) {
                    clickCount++; // 增加一次点击数
                    button2.setText("点击了: " + clickCount + " 次"); // 重新设置按钮二的标签
                  }
              });
 
      setSize(300, 200); // 设置窗口尺寸
      setVisible(true); // 设置窗口可视
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口时退出程序
    }
 
    public static void main(String[] args) {
      new RoundButtonDemo();
    }
}
 * 
 */