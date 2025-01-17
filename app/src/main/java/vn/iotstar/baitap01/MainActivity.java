package vn.iotstar.baitap01;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Câu 4:
        // Ánh xạ các layout
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        LinearLayout evenOddLayout = findViewById(R.id.evenOddLayout);

        // Nút trong layout chính
        Button btnEvenOdd = findViewById(R.id.btnEvenOdd);

        // Nút trong layout số chẵn lẻ
        Button btnProcessArray = findViewById(R.id.btnProcessArray);
        Button btnBack = findViewById(R.id.btnBack);

        // Các view trong layout số chẵn lẻ
        EditText edtInputArray = findViewById(R.id.edtInputArray);
        TextView tvResult = findViewById(R.id.tvResult);

        // Chuyển từ layout chính sang layout số chẵn lẻ
        btnEvenOdd.setOnClickListener(v -> {
            mainLayout.setVisibility(View.GONE); // Ẩn layout chính
            evenOddLayout.setVisibility(View.VISIBLE); // Hiển thị layout số chẵn lẻ
        });

        // Xử lý logic phân loại số chẵn và số lẻ
        btnProcessArray.setOnClickListener(v -> {
            String input = edtInputArray.getText().toString().trim();

            if (input.isEmpty()) {
                Toast.makeText(MainActivity.this, "Vui lòng nhập mảng số!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                // Chuyển chuỗi thành mảng số nguyên
                String[] stringNumbers = input.split(",");
                ArrayList<Integer> numbers = new ArrayList<>();
                for (String num : stringNumbers) {
                    numbers.add(Integer.parseInt(num.trim()));
                }

                // Phân loại số chẵn và số lẻ
                ArrayList<Integer> evenNumbers = new ArrayList<>();
                ArrayList<Integer> oddNumbers = new ArrayList<>();

                for (int num : numbers) {
                    if (num % 2 == 0) {
                        evenNumbers.add(num);
                    } else {
                        oddNumbers.add(num);
                    }
                }

                // Hiển thị kết quả
                String result = "Số chẵn: " + evenNumbers + "\nSố lẻ: " + oddNumbers;
                tvResult.setText(result);

                // In ra Logcat
                Log.d("EvenNumbers", evenNumbers.toString());
                Log.d("OddNumbers", oddNumbers.toString());

            } catch (NumberFormatException e) {
                Toast.makeText(MainActivity.this, "Định dạng không hợp lệ! Vui lòng nhập số cách nhau bởi dấu phẩy.", Toast.LENGTH_SHORT).show();
            }
        });

        // Quay lại layout chính
        btnBack.setOnClickListener(v -> {
            evenOddLayout.setVisibility(View.GONE); // Ẩn layout số chẵn lẻ
            mainLayout.setVisibility(View.VISIBLE); // Hiển thị layout chính
        });

        //Câu 5: Viết chương trình nhập 01 chuỗi ký tự bất kỳ từ View (EditText) và in ra View (TextView) và in ra Toast chuỗi đó đảo ngược và in hoa thông qua nút Button. ví dụ: s="I LOVE YOU" -> s="YOU LOVE I"
        // Ánh xạ các view
        EditText edtInput = findViewById(R.id.edtInput);
        Button btnProcess = findViewById(R.id.btnProcess);

        // Sự kiện khi nhấn nút "Đảo ngược và in hoa"
        btnProcess.setOnClickListener(v -> {
            // Lấy chuỗi người dùng nhập vào
            String inputText = edtInput.getText().toString().trim();

            // Kiểm tra nếu chuỗi không rỗng
            if (!inputText.isEmpty()) {
                // Đảo ngược chuỗi và chuyển thành in hoa
                String reversedText = reverseAndUppercase(inputText);

                // Hiển thị kết quả dưới dạng Toast
                Toast.makeText(MainActivity.this, reversedText, Toast.LENGTH_LONG).show();
            } else {
                // Nếu người dùng chưa nhập gì
                Toast.makeText(MainActivity.this, "Vui lòng nhập chuỗi!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Phương thức đảo ngược chuỗi và chuyển thành in hoa
    private String reverseAndUppercase(String input) {
        // Chuyển chuỗi thành mảng từ các từ
        String[] words = input.split(" ");

        // Đảo ngược mảng từ
        StringBuilder reversedString = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedString.append(words[i]).append(" ");
        }

        // Chuyển chuỗi đảo ngược thành in hoa và trả về
        return reversedString.toString().toUpperCase().trim();
    }
}